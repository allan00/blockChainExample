package com.webank.blockchain.main;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webank.blockchain.domain.Block;
import com.webank.blockchain.domain.BlockChain;
import com.webank.blockchain.domain.Record;
import com.webank.blockchain.util.Client;

@RestController
public class BlockChainController {

	Block bc = new Block(0,0);
	Block lastBc = bc;

	BlockChain bco = new BlockChain(bc);
	@CrossOrigin
	@RequestMapping(value = "/queryBlock", method = RequestMethod.GET)
	public String bc() {
		return bc.toJson();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/pushAddRequest", method = RequestMethod.POST)
	public String pushAdd(@RequestBody String rec) throws UnsupportedEncodingException {
		String str = java.net.URLDecoder.decode(rec,"utf-8");
		if(!str.equals(rec)) {
			str = str.substring(0,str.length()-1);
		}
		//Record l = new Record(1,100,new Timestamp(System.currentTimeMillis()),str,"10.106.11.22");
		
		JSONObject jsonobj = JSONObject.fromObject(str,new JsonConfig());
		Record r = new Record();
//			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			String generateTime = sdf.format(time);
		//Record r=new Record();
		r.setCommand((Integer) jsonobj.get("command"));
		r.setAmount((Double) jsonobj.get("amount"));
		r.setTime(Timestamp.valueOf((String) jsonobj.get("time")));
		r.setRemark((String) jsonobj.get("remark"));
		r.setIp((String) jsonobj.get("ip"));

			
		BlockChain.add(bc, r);
		bc = bco.getBlock();
		//String res = JsonTools.createJsonString("lastBlock", bc);
		//return bc.toJson();
		return "success";
	}

//	@CrossOrigin
//	@RequestMapping(value = "/pushAddRequest", method = RequestMethod.POST)
//	public String pushAdd(@RequestBody String req) throws UnsupportedEncodingException {
//		String str = java.net.URLDecoder.decode(req,"utf-8");
//		if(!str.equals(req)) {
//			str = str.substring(0,str.length()-1);
//		}
//		Record l = new Record(1,100,new Timestamp(System.currentTimeMillis()),str,"10.106.11.22");
//		BlockChain.add(bc, l);
//		bc = bco.getBlock();
//		//String res = JsonTools.createJsonString("lastBlock", bc);
//		//return bc.toJson();
//		return "success";
//	}
	
//	@RequestMapping(value = "/log", method = RequestMethod.GET)
//    public String log() {
//		com.webank.blockchain.domain.Log l=new com.webank.blockchain.domain.Log("add");
//		l.setAmount(100);
//		l.setIp("192.168.0.11");
//		l.setDate(new Date(System.currentTimeMillis()));
//		l.setMsg("ccc");	
//		JsonTools js=new JsonTools();
//		String logJson=js.createJsonString("log1", l);
//		return logJson;
//    }
	
	@CrossOrigin
	
	@RequestMapping(value = "/addBlock", method = RequestMethod.POST)
	public String add(@RequestBody String requestJson) throws UnsupportedEncodingException {
		String str = java.net.URLDecoder.decode(requestJson,"utf-8");
		JSONObject outer_jsonobj = JSONObject.fromObject(str,new JsonConfig());;
		JSONObject jsonobj = JSONObject.fromObject(outer_jsonobj.get("msgBody"));
		String ip = "";
		String result = "";
		Record r = new Record();
		try{
			//ip = InetAddress.getLocalHost().getHostAddress();
			ip = "8082";
			Timestamp time = new Timestamp(System.currentTimeMillis());
//			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			String generateTime = sdf.format(time);
			//Record r=new Record();
			r.setCommand((Integer) jsonobj.get("command"));
			r.setAmount((Integer) jsonobj.get("amount"));
			r.setTime(time);
			r.setRemark((String) jsonobj.get("remark"));
			r.setIp(ip);
			result += Client.sendPost("http://119.29.98.174:8080/pushAddRequest", r.toString());
			result += Client.sendPost("http://119.29.98.174:8081/pushAddRequest", r.toString());
			result += Client.sendPost("http://119.29.98.174:8082/pushAddRequest", r.toString());
			
//		Client.sendPost("http://localhost:8081/pushAddRequest", jsonobj);
//		Client.sendPost("http://localhost:8082/pushAddRequest", jsonobj);
		}
		catch (Exception e){
			System.out.println(e);
		}
		return result;
	}
	
}

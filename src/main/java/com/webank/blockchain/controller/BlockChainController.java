package com.webank.blockchain.controller;

import java.net.InetAddress;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.webank.blockchain.domain.Block;
import com.webank.blockchain.domain.BlockChain;
import com.webank.blockchain.domain.Record;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.web.bind.annotation.*;

import com.webank.blockchain.util.Client;
import com.webank.blockchain.util.JsonTools;

@RestController
public class BlockChainController {

	Block bc = new Block(0,0);
	Block lastBc = bc;

	BlockChain bco = new BlockChain(bc);

	@RequestMapping(value = "/queryBlock", method = RequestMethod.GET)
	public String bc() {
		return bc.toString();
	}

	@RequestMapping(value = "/addBlock", method = RequestMethod.POST)
	public String add(@RequestBody String requestJson) {
		JSONObject jsonobj = JSONObject.fromObject(requestJson,new JsonConfig());
		String ip = "";
		String result = "";
		try{
			ip = InetAddress.getLocalHost().getHostAddress();
			Timestamp time = new Timestamp(System.currentTimeMillis());
//			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			String generateTime = sdf.format(time);
			Record r=new Record();
			r.setCommand((Integer) jsonobj.get("command"));
			r.setAmount(Double.valueOf((String) jsonobj.get("amount")));
			r.setTime(time);
			r.setRemark((String) jsonobj.get("remark"));
			r.setIp(ip);

			result += Client.sendPost("http://localhost:8080/pushAddRequest", jsonobj);
//		Client.sendPost("http://localhost:8081/pushAddRequest", jsonobj);
//		Client.sendPost("http://localhost:8082/pushAddRequest", jsonobj);
		}
		catch (Exception e){

		}
		return result;
	}
	
	@RequestMapping(value = "/pushAddRequest", method = RequestMethod.POST)
	public String pushAdd(@RequestParam("par1") String par1,@RequestParam("par2") String par2,@RequestParam("par3") String par3) {
		Record r = new Record();
		BlockChain.add(r);
		bc = bco.getBlock();
		return bc.toString();
	}
	
	@RequestMapping(value = "/record", method = RequestMethod.GET)
    public String log() {
		Record r=new Record();
		r.setAmount(100);
		r.setIp("192.168.0.11");
		r.setCommand(2);
		JsonTools js=new JsonTools();
		String logJson=js.createJsonString("log1", r);
		return logJson;
    }
}

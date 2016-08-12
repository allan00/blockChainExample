package com.webank.blockchain.main;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.script.ScriptException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.web.bind.annotation.*;

import com.webank.blockchain.dao.BlockChainDaoImp;
import com.webank.blockchain.domain.Block;
import com.webank.blockchain.domain.BlockChain;
import com.webank.blockchain.domain.JSCommand;
import com.webank.blockchain.domain.Record;
import com.webank.blockchain.util.Client;
import com.webank.blockchain.util.Constants;
import com.webank.blockchain.util.JavaScriptEngine;

@RestController
public class BlockChainController {

	Block bc = new Block(0,0);
	Block lastBc = bc;
	Double total = 0.0;
	BlockChain bco = new BlockChain(bc);
	
    static ConcurrentHashMap<String,List> ip_records = new ConcurrentHashMap<String, List>();
    
	@CrossOrigin
	@RequestMapping(value = "/queryBlock", method = RequestMethod.GET)
	public String bc() {
		return "{\"total\":"+total+", \"block\":"+bc.toJson()+"}";
	}
	
	@CrossOrigin
	@RequestMapping(value = "/queryAllBlock", method = RequestMethod.GET)
	public String allBc() {
		BlockChainDaoImp dao = new BlockChainDaoImp();
		List<Block> list = dao.selectAll();
		Iterator it = list.iterator();
		String res = "[";
		while(it.hasNext()) {
			Block b = (Block)it.next();
			res = res + b.toJson() + ",";
		}
		res = res + bc.toJson() + "]";
		return res;
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
        String ip = (String)jsonobj.get("ip");
		Record r = new Record();
//			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			String generateTime = sdf.format(time);
		//Record r=new Record();
		
		int opt = (Integer) jsonobj.get("command");
		Double amt = (Double) jsonobj.get("amount");
		if (opt == 1) {
			total = total + amt;
		} else if (opt == 2) {
			total = total - amt;
		}
		
		r.setCommand((Integer) jsonobj.get("command"));
		r.setAmount(jsonobj.getDouble("amount"));
		r.setTime(Timestamp.valueOf((String) jsonobj.get("time")));
		r.setRemark((String) jsonobj.get("remark"));
		r.setIp((String) jsonobj.get("ip"));

			
		BlockChain.add(bc, r);
		bc = bco.getBlock();
		//String res = JsonTools.createJsonString("lastBlock", bc);
		//return bc.toJson();
        //按ip将Record分类，一个特定的ip对应在该ip下请求的Records
        List list = ip_records.get(ip);
        if(list==null){
            list = new ArrayList<Record>();
            ip_records.put(ip,list);
        }
        list.add(r);
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
		String str = java.net.URLDecoder.decode(requestJson, "utf-8");
		JSONObject outer_jsonobj = JSONObject.fromObject(str, new JsonConfig());
		JSONObject jsonobj = JSONObject.fromObject(outer_jsonobj.get("msgBody"));
		String ip = Constants.PORT_S;
		String result = "";
		Record r = new Record();
		String jString="";
		if(jsonobj.get("command")==null)
		{
			return result;
		}
		int c=(Integer) jsonobj.get("command");
		if (c==3) {
			jString=(String) jsonobj.get("js");
			if (jString != null && !jString.equals("")) {
				try {
					String jsons = (String) JavaScriptEngine.engine3(jString);// 参数为jString
					ArrayList<JSCommand> list = new ArrayList<JSCommand>();
					JSONArray jsonArray = JSONArray.fromObject(jsons);
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						JSCommand jsCommand = new JSCommand();
						jsCommand.setCommand((Integer) jsonObject.get("command"));
						jsCommand.setAmount(jsonObject.getDouble("amount"));
						jsCommand.setRemark((String) jsonObject.get("remark"));
						list.add(jsCommand);
					}
					for (int i = 0; i < list.size(); i++) {
						switch (list.get(i).getCommand()) {
						case 1://捐款
							r.setCommand(1);
							r.setAmount(list.get(i).getAmount());
							r.setTime(new Timestamp(System.currentTimeMillis()));
							r.setRemark(list.get(i).getRemark());
							r.setIp(ip);
							String s1=java.net.URLEncoder.encode(r.toString(), "utf-8");
							result += Client.sendPost("http://localhost:8080/pushAddRequest", s1);
							result += Client.sendPost("http://localhost:8081/pushAddRequest", s1);
							result += Client.sendPost("http://localhost:8082/pushAddRequest", s1);
							break;
						case 2://提款
							r.setCommand(1);
							r.setAmount(list.get(i).getAmount());
							r.setTime(new Timestamp(System.currentTimeMillis()));
							r.setRemark(list.get(i).getRemark());
							r.setIp(ip);
							String s2=java.net.URLEncoder.encode(r.toString(), "utf-8");
							result += Client.sendPost("http://localhost:8080/pushAddRequest", s2);
							result += Client.sendPost("http://localhost:8081/pushAddRequest", s2);
							result += Client.sendPost("http://localhost:8082/pushAddRequest", s2);
							break;	
						default:
							break;
						}
						System.out.println(list.get(i));
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (ScriptException e) {
					e.printStackTrace();
				}
			}
		}else{
			try{
				//ip = InetAddress.getLocalHost().getHostAddress();
				Timestamp time = new Timestamp(System.currentTimeMillis());
//				DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//				String generateTime = sdf.format(time);
				//Record r=new Record();
				r.setCommand((Integer) jsonobj.get("command"));
				r.setAmount(jsonobj.getDouble("amount"));
				r.setTime(time);
				r.setRemark((String) jsonobj.get("remark"));
				r.setIp(ip);
				String s=java.net.URLEncoder.encode(r.toString(), "utf-8");
				result += Client.sendPost("http://localhost:8080/pushAddRequest", s);
				result += Client.sendPost("http://localhost:8081/pushAddRequest", s);
				result += Client.sendPost("http://localhost:8082/pushAddRequest", s);

			}
			catch (Exception e){
				System.out.println(e);
			}
		}
		return result;
	}


	@CrossOrigin
	@RequestMapping(value = "/queryRecordByIP", method = RequestMethod.POST)
	public String getRecordList(@RequestBody String jsonip) throws UnsupportedEncodingException {
	    String str = java.net.URLDecoder.decode(jsonip, "utf-8");
	    JSONObject jsonobj = JSONObject.fromObject(str, new JsonConfig());
	    System.out.println("json="+str);
	    String ip = "";
	    if(jsonobj.get("ip")!=null) {
	        ip = jsonobj.getString("ip");
	    }
	    else{
	        return "";
	    }
	    List list = ip_records.get(ip);
	    if(list==null)
	        return "null";
	    Iterator it = list.iterator();
	    String res = "[";
	    while(it.hasNext()) {
	        res = res +  ((Record)it.next()).toString()+",";
	    }
	    if(list.size()>0) {
	        res = res.substring(0,res.length()-1);
	    }
	    res += "]";
	    return res;
	}
	
}

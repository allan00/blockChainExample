package com.webank.blockchain.main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.webank.blockchain.domain.Record;

public class Test {

//	public static void main(String[] args) throws UnknownHostException {
//		// TODO Auto-generated method stub
//		String requestJson = "{\"type\": \"normal\",\"msgBody\": {\"command\": 2,\"amount\":10,\"remark\":\"test\",\"js\":\"\"}}";
//		JSONObject outer_jsonobj = JSONObject.fromObject(requestJson,new JsonConfig());;
//		JSONObject jsonobj = JSONObject.fromObject(outer_jsonobj.get("msgBody"));
//		String ip = "";
//		String result = "";
//		Record r = new Record();
//
//		ip = InetAddress.getLocalHost().getHostAddress();
//		Timestamp time = new Timestamp(System.currentTimeMillis());
////			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
////			String generateTime = sdf.format(time);
//		//Record r=new Record();
//		r.setCommand((Integer) jsonobj.get("command"));
//		r.setAmount((Integer) jsonobj.get("amount"));
//		r.setTime(time);
//		r.setRemark((String) jsonobj.get("remark"));
//		r.setIp(ip);
//		System.out.println(r);
//			//result += Client.sendPost("http://localhost:8080/pushAddRequest", r);
//			
////		Client.sendPost("http://localhost:8081/pushAddRequest", jsonobj);
////		Client.sendPost("http://localhost:8082/pushAddRequest", jsonobj);
//
//	}

}

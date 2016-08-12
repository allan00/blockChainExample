package com.webank.blockchain.main;

import java.sql.Timestamp;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class PaxosController {
	
	String accepted = null;
	Boolean locked = false;
	Timestamp epoch = null;
	
	@CrossOrigin
	@RequestMapping(value = "/prepare", method = RequestMethod.POST)
	public String prepare(@RequestBody String ts) {
		if(locked) {
			if(epoch!=null&&epoch.getTime()<Timestamp.valueOf(ts).getTime()) {
				epoch = Timestamp.valueOf(ts);
			}
		} else {
			if(accepted.equals(null)) {
				
			}
		}
		return "";
	}
	
	@CrossOrigin
	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	public String accept(@RequestBody String str) {
		
		return "";
	}
}

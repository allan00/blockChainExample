package com.webank.blockchain.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import com.webank.blockchain.domain.Block;
import com.webank.blockchain.domain.BlockChain;
import com.webank.blockchain.domain.Record;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public void add(@RequestParam("par1") String par1,@RequestParam("par2") String par2,@RequestParam("par3") String par3) {
		Map parameters = new HashMap<String,String>();
		parameters.put("par1",par1);
		parameters.put("par2",par2);
		parameters.put("par3",par3);

		Client.sendPost("http://localhost:8080/pushAddRequest", parameters);
		Client.sendPost("http://localhost:8081/pushAddRequest", parameters);
		Client.sendPost("http://localhost:8082/pushAddRequest", parameters);
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

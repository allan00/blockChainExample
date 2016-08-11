package com.webank.blockchain.main;

import java.sql.Date;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webank.blockchain.blockchains.Block;
import com.webank.blockchain.blockchains.BlockChain;
import com.webank.blockchain.blockchains.Log;
import com.webank.blockchain.util.Client;
import com.webank.blockchain.util.JsonTools;

@RestController
public class BlockChainController {

	Block bc = new Block("test");
	Block lastBc = bc;

	BlockChain bco = new BlockChain(bc);

	@RequestMapping(value = "/queryBlock", method = RequestMethod.GET)
	public String bc() {
		return bc.toString();
	}

	@RequestMapping(value = "/addBlock", method = RequestMethod.GET)
	public void add() {
		Map parameters = null;
		Client.sendGet("http://localhost:8080/pushAddRequest", parameters);
		Client.sendGet("http://localhost:8081/pushAddRequest", parameters);
		Client.sendGet("http://localhost:8082/pushAddRequest", parameters);
	}
	
	@RequestMapping(value = "/pushAddRequest", method = RequestMethod.GET)
	public String pushAdd() {
		Log l = new Log("TEST");
		BlockChain.add(bc, l);
		bc = bco.getBlock();
		return bc.toString();
	}
	
	@RequestMapping(value = "/log", method = RequestMethod.GET)
    public String log() {
		com.webank.blockchain.domain.Log l=new com.webank.blockchain.domain.Log("add");
		l.setAmount(100);
		l.setIp("192.168.0.11");
		l.setDate(new Date(System.currentTimeMillis()));
		l.setMsg("ccc");	
		JsonTools js=new JsonTools();
		String logJson=js.createJsonString("log1", l);
		return logJson;
    }
}

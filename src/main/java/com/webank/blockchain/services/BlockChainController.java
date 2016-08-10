package com.webank.blockchain.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webank.blockchain.blockchains.Block;
import com.webank.blockchain.blockchains.BlockChain;
import com.webank.blockchain.blockchains.Log;

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
	public String add() {
		Log l = new Log("TEST");
		BlockChain.add(bc, l);
		bc = bco.getBlock();
		return bc.toString();
	}
	
}

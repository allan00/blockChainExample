package com.webank.blockchain.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webank.blockchain.blockchains.BlockChain;
import com.webank.blockchain.blockchains.BlockChainOpt;
import com.webank.blockchain.blockchains.Log;

@RestController
public class BlockChainController {
	
	BlockChain bc = new BlockChain("test");
	BlockChain lastBc = bc;
	
	BlockChainOpt bco = new BlockChainOpt(bc);
	
	@RequestMapping(value = "/bc", method = RequestMethod.GET)
    public String bc() {
        return bc.toString();
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
		Log l = new Log("TEST");
		BlockChainOpt.add(bc, l);
		bc = bco.getBc();
        return bc.toString();
    }
	
}

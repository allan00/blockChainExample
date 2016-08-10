package com.webank.blockchain.blockchains;

import java.util.ArrayList;


public class BlockChain {
	
	private String prevHash;;
	
	private String hash;
	//private Date lastHit;
	
	private ArrayList<Log> body = new ArrayList<Log>();
	
	public BlockChain(String prevHash) {
		this.prevHash =prevHash;
	}
	
	public int size() {
		return body.size();
	}
	
	public void add(Log log) {
		if(this.size() <= 5) {
			body.add(log);
		}
	}

	@Override
	public String toString() {
		return "BlockChain [prevHash=" + prevHash + ", hash=" + hash
				+ ", body=" + body + "]";
	}

	public String getHash() {
		return hash;
	}
	
	
}

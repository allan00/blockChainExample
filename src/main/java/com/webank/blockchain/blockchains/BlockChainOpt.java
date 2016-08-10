package com.webank.blockchain.blockchains;

public class BlockChainOpt {

	private static BlockChain lastBc;
	
	public BlockChainOpt() {}
	
	public BlockChainOpt(BlockChain bc) {
		this.lastBc = bc;
	}
	
	public static void add(BlockChain bc, Log l) {
		if (bc.size() <=5 ) {
			bc.add(l);
		} else {
			BlockChain prevBc = lastBc;
		    lastBc = new BlockChain(prevBc.getHash());
		}
	}
	
	public BlockChain getBc() {
		return lastBc;
	}
}

package com.webank.blockchain.blockchains;

public class BlockChain {

	private static Block lastBc;

	public BlockChain(Block bc) {
		lastBc = bc;
	}

	public static void add(Block bc, Log l) {
		if (bc.size() < Block.MAX_SIZE) {
			bc.add(l);
		} else {
			Block prevBc = lastBc;
			lastBc = new Block(prevBc.getIndex(),prevBc.hashCode());
			lastBc.add(l);
		}
	}

	public Block getBlock() {
		return lastBc;
	}
}

package com.webank.blockchain.domain;

public class BlockChain {

	private static Block lastBc;

	public BlockChain(Block bc) {
		lastBc = bc;
	}

	public static void add(Block bc, Record r) {
		if (bc.size() < Block.MAX_SIZE) {
			bc.add(r);
		} else {
			Block prevBc = lastBc;
			lastBc = new Block(prevBc.getIndex(),prevBc.hashCode());
			lastBc.add(r);
		}
	}

	public Block getBlock() {
		return lastBc;
	}
}

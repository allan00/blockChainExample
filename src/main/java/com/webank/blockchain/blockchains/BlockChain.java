package com.webank.blockchain.blockchains;

public class BlockChain {

	private static Block lastBc;

	public BlockChain() {
	}

	public BlockChain(Block bc) {
		this.lastBc = bc;
	}

	public static void add(Block bc, Log l) {
		if (bc.size() <= 5) {
			bc.add(l);
		} else {
			Block prevBc = lastBc;
			lastBc = new Block(prevBc.getHash());
		}
	}

	public Block getBlock() {
		return lastBc;
	}
}

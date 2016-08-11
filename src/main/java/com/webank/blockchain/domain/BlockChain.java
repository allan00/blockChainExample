package com.webank.blockchain.blockchains;

import com.webank.blockchain.domain.Record;

public class BlockChain {

	private static Block lastBc;

	public BlockChain(Block bc) {
		lastBc = bc;
	}

	public static void add(Record r) {
		if (lastBc.size() <= lastBc.MAX_SIZE) {
			lastBc.add(r);
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

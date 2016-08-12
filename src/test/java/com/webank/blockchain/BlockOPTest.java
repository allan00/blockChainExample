package com.webank.blockchain;

import java.util.List;

import com.webank.blockchain.dao.BlockChainDaoImp;
import com.webank.blockchain.domain.Block;

public class BlockOPTest { 

	public static void main(String[] args) {
		BlockChainDaoImp dao = new BlockChainDaoImp();
		List<Block> list = dao.selectAll();
		System.out.println(list.size());
	}
}

package com.webank.blockchain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import com.webank.blockchain.dao.BlockChainDaoImp;
import com.webank.blockchain.domain.Block;
import com.webank.blockchain.domain.Record;

public class BlockOPTest { 

	public static void main(String[] args) {
		BlockChainDaoImp dao = new BlockChainDaoImp();
		//List<Block> list = dao.selectAll();
		//System.out.println(list.size());
		
		Block block=new Block();
		block.setIndex(1);
		ArrayList<Record> list2=new ArrayList<Record>();
		list2.add(new Record());
		block.setBody(list2);
		
		System.out.println(dao.insertBlock(block));
		
		
		
		
	}
}

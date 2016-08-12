package com.webank.blockchain.dao;

import java.util.List;

import com.webank.blockchain.domain.Block;

public interface IBlockChainDao {

	public int insertBlock(Block block);
	public int updateBlock(Block block);
	public List<Block> selectBlock(int index);
}

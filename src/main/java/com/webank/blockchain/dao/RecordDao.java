package com.webank.blockchain.dao;

import java.util.List;

import com.webank.blockchain.domain.Block;
import com.webank.blockchain.domain.Record;

/**
 * 图书类型数据访问接口
 *
 */
public interface RecordDao {
	/*
	 * 获得所有图书类型
	 */
	//public List<Block> getAllBlock();
	
	public Record getById(int id);
}

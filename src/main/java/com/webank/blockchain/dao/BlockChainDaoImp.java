package com.webank.blockchain.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.webank.blockchain.domain.Block;
import com.webank.blockchain.mapping.IBlockChainMapper;
import com.webank.blockchain.util.MyBatisUtil;

public class BlockChainDaoImp implements IBlockChainDao{

	public int insertBlock(Block block) {
		// TODO Auto-generated method stub
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 得到AdminInfoMapper接口的实现类对象，IAdminInfoMapper接口的实现类对象由sqlSession.getMapper(IAdminInfoMapper.class)动态构建出来
        IBlockChainMapper mapper = sqlSession.getMapper(IBlockChainMapper.class);
        // 执行查询操作，将查询结果自动封装成User返回
        mapper.addBlock(block);
        sqlSession.close();
		return 0;
	}

	public int updateBlock(Block block) {
		// TODO Auto-generated method stub
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 得到AdminInfoMapper接口的实现类对象，IAdminInfoMapper接口的实现类对象由sqlSession.getMapper(IAdminInfoMapper.class)动态构建出来
        IBlockChainMapper mapper = sqlSession.getMapper(IBlockChainMapper.class);
        // 执行查询操作，将查询结果自动封装成User返回
        mapper.updateBlock(block);
        sqlSession.close();
		return 0;
	}

	public List<Block> selectBlock(int index) {
		// TODO Auto-generated method stub
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 得到AdminInfoMapper接口的实现类对象，IAdminInfoMapper接口的实现类对象由sqlSession.getMapper(IAdminInfoMapper.class)动态构建出来
        IBlockChainMapper mapper = sqlSession.getMapper(IBlockChainMapper.class);
        // 执行查询操作，将查询结果自动封装成User返回
        Block block = mapper.selectByIndex(index);
        List<Block> list  = new ArrayList<Block>();
        list.add(0, block);
        while(block.getPrevIndex() != 0) {
          block = mapper.selectByIndex(block.getPrevIndex());
          list.add(0, block); 
        }
        sqlSession.close();
		return list;
	}

}

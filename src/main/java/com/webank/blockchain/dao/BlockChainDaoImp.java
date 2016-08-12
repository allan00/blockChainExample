package com.webank.blockchain.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import org.apache.ibatis.session.SqlSession;

import com.webank.blockchain.domain.Block;
import com.webank.blockchain.domain.TB_Block;
import com.webank.blockchain.mapping.IBlockChainMapper;
import com.webank.blockchain.util.MyBatisUtil;
import com.webank.blockchain.util.SerializeTool;

public class BlockChainDaoImp implements IBlockChainDao{

	public int insertBlock(Block block) {
/*		// TODO Auto-generated method stub
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 得到AdminInfoMapper接口的实现类对象，IAdminInfoMapper接口的实现类对象由sqlSession.getMapper(IAdminInfoMapper.class)动态构建出来
        IBlockChainMapper mapper = sqlSession.getMapper(IBlockChainMapper.class);
        // 执行查询操作，将查询结果自动封装成User返回
        TB_Block tb_Block = new TB_Block();
        //tb_Block.setId(block.getIndex());
        try {
			tb_Block.setBody(SerializeTool.serializeObject(block));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        mapper.addBlock(tb_Block);
        sqlSession.close();
		return 0;*/
    	
		byte[] bytes = null;
		try {
			System.out.println("remark before insert:"+block.getBody().get(0).getRemark());
			bytes = SerializeTool.serializeObject(block);
		} catch (IOException e) {
			e.printStackTrace();
		}
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        java.sql.Connection conn=null;
        String sql= "insert into tb_block (id,body) values(?,?)"; 
		try {
			conn=sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, block.getIndex());
			ps.setBytes(2, bytes);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int updateBlock(Block block) {
		// TODO Auto-generated method stub
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 得到AdminInfoMapper接口的实现类对象，IAdminInfoMapper接口的实现类对象由sqlSession.getMapper(IAdminInfoMapper.class)动态构建出来
        IBlockChainMapper mapper = sqlSession.getMapper(IBlockChainMapper.class);
        // 执行查询操作，将查询结果自动封装成User返回
        TB_Block tb_Block = new TB_Block();
        tb_Block.setId(block.getIndex());
        try {
			tb_Block.setBody(SerializeTool.serializeObject(block));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        mapper.updateBlock(tb_Block);
        sqlSession.close();
		return 0;
	}

	public List<Block> selectBlock(int index) {
		/*// TODO Auto-generated method stub
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 得到AdminInfoMapper接口的实现类对象，IAdminInfoMapper接口的实现类对象由sqlSession.getMapper(IAdminInfoMapper.class)动态构建出来
        IBlockChainMapper mapper = sqlSession.getMapper(IBlockChainMapper.class);
        // 执行查询操作，将查询结果自动封装成User返回
        Block block = mapper.selectByIndex(index);*/
        List<Block> list  = new ArrayList<Block>();
     /*   list.add(0, block);
        while(block.getPrevIndex() != 0) {
          block = mapper.selectByIndex(block.getPrevIndex());
          list.add(0, block); 
        }*/
        //sqlSession.close();
		return list;
	}
	
	public List<Block> selectAll() {
		// TODO Auto-generated method stub
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 得到AdminInfoMapper接口的实现类对象，IAdminInfoMapper接口的实现类对象由sqlSession.getMapper(IAdminInfoMapper.class)动态构建出来
        IBlockChainMapper mapper = sqlSession.getMapper(IBlockChainMapper.class);
        // 执行查询操作，将查询结果自动封装成User返回
        List<TB_Block> list = mapper.selectAll();
        List<Block> blockList = new ArrayList<Block>();
        TB_Block tb_Block=null;
        for (int i= 0; i<list.size(); i++) {
        	Block block = null;
			try {
				tb_Block=list.get(i);
				block = (Block) SerializeTool.deserializeObject(tb_Block.body);
				System.out.println("remark after select:"+block.getBody().get(0).getRemark());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			if(block != null) {
				blockList.add(block);
			}
        	
        }
        sqlSession.close();
        return blockList;
	}

}

package com.webank.blockchain;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import com.webank.blockchain.dao.BlockDao;
import com.webank.blockchain.dao.BlockDaoImpl;
import com.webank.blockchain.domain.Block;
import com.webank.blockchain.domain.Record;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTest {

    public static void main(String[] args) throws IOException {
    	
    	BlockDao blockDao=new BlockDaoImpl();
        
		Record r=blockDao.getById(1);
		System.out.println(r);

    }
}
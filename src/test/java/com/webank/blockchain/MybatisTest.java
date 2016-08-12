package com.webank.blockchain;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import com.webank.blockchain.dao.RecordDao;
import com.webank.blockchain.dao.RecordDaoImpl;
import com.webank.blockchain.domain.Block;
import com.webank.blockchain.domain.Record;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTest {
    public static void main(String[] args) throws IOException {
    	RecordDao blockDao=new RecordDaoImpl();
		/*
    	Record r=blockDao.getById(1);
		System.out.println(r);
		*/
/*    Record[] records=new Record[100];
    for (int i = 0; i < records.length; i++) {
		records[i]=new Record();
		records[i].setIp("2342");
	}
    blockDao.insert(records);*/
    	for (int i = 0; i < 100; i++) {
			
    		Record record=new Record();
    		record.setIp("1980");
    		record.setAmount(1233);
    		blockDao.insert(record);
		}
			
		
    }
}
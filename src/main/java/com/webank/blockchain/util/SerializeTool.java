package com.webank.blockchain.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;

import org.apache.ibatis.session.SqlSession;

import com.webank.blockchain.domain.Record;
import com.webank.blockchain.mapping.IBlockMapper;



public class SerializeTool {
	
	 /** 序列化对象 
	 * ＠throws IOException */ 
	public static byte[] serializeObject(Object object) throws IOException{ 
	       ByteArrayOutputStream stream=new ByteArrayOutputStream(); 
	       ObjectOutputStream oos=new ObjectOutputStream(stream); 
	       oos.writeObject(object);
	       oos.flush(); 
	       return stream.toByteArray();
	    } 
	
	
	/** 反序列化对象 
     * ＠throws IOException 
     * ＠throws ClassNotFoundException */ 
	
    public static Object deserializeObject(byte[] buf) throws IOException,ClassNotFoundException{ 

       Object object=null; 

       ByteArrayInputStream stream=new ByteArrayInputStream(buf); 

       ObjectInputStream ois = new ObjectInputStream(stream); 

       object=(Object) ois.readObject(); 

       return object; 
    } 


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Record r = new Record();
		r.setAmount(100);
		r.setIp("192.168.0.11");
		byte[] bytes = null;
		try {
			bytes = serializeObject(r);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        
        //SqlMapper sqlMapper = new SqlMapper(sqlSession);
        sqlSession.insert("insert into tb_block(body) values(#{body})",bytes);
		
	}

}

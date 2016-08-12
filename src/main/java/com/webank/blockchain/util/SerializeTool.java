package com.webank.blockchain.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import com.github.abel533.sql.SqlMapper;
import com.mysql.jdbc.PreparedStatement;
import com.webank.blockchain.domain.Record;


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

}

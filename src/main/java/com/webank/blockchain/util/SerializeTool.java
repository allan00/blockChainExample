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


	public static void main(String[] args) {
		Record r = new Record();
		r.setAmount(100);
		r.setIp("192.168.0.11");
		byte[] bytes = null;
		try {
			bytes = serializeObject(r);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        java.sql.Connection conn=null;
        String sql= "insert into tb_block (body) values(?)"; 
		try {
			conn=sqlSession.getConfiguration().getEnvironment().getDataSource().getConnection();
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setBytes(1, bytes);
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
		
		//反序列化
        String sql1= "select body from tb_block where id=?";
        try {
			PreparedStatement ps1=(PreparedStatement) conn.prepareStatement(sql1);
			ps1.setInt(1, 1);
			ResultSet resultSet=ps1.executeQuery();
			if (resultSet.next()) {
				byte[] body=resultSet.getBytes("body");
				try {
					Record record=(Record) deserializeObject(body);
					System.out.println(record);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
	}
}

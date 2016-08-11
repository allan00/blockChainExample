package com.webank.blockchain.mapping;


import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.webank.blockchain.domain.Block;
import com.webank.blockchain.domain.Record;
 
public interface IBlockMapper {
     
    //使用@Select注解指明getById方法要执行的SQL
    @Select("select * from tb_record where id=#{id}")
    public Record getById(int id);
     
    //使用@Select注解指明getAll方法要执行的SQL
    @Select("select * from tb_record")
    public List<Record> getAll();
     
    //使用@Insert注解指明add方法要执行的SQL
    @Insert("insert into tb_record(ip) values(#{ip})")
    public int add(Record user);
     
    //使用@Update注解指明update方法要执行的SQL
    @Update("update tb_record set ip=#{ip} where id=#{id}")
    public int update(Record user);
     
    //使用@Delete注解指明deleteById方法要执行的SQL
    @Delete("delete from tb_record where id=#{id}")
    public int deleteById(int id);
}
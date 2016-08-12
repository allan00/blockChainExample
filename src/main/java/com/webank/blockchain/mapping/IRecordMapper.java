package com.webank.blockchain.mapping;


import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.webank.blockchain.domain.Block;
import com.webank.blockchain.domain.Record;
 
public interface IRecordMapper {
     
    //使用@Select注解指明getById方法要执行的SQL
    @Select("select * from record where id=#{id}")
    public Record getById(int id);
     
    //使用@Select注解指明getAll方法要执行的SQL
    @Select("select * from record")
    public List<Record> getAll();
     
    //使用@Insert注解指明add方法要执行的SQL
    @Insert("insert into record(command,time,remark,ip) values(#{command},#{time},#{remark},#{ip})")
    public int add(Record record);
     
    //使用@Update注解指明update方法要执行的SQL
    @Update("update record set ip=#{ip} where id=#{id}")
    public int update(Record record);
     
    //使用@Delete注解指明deleteById方法要执行的SQL
    @Delete("delete from record where id=#{id}")
    public int deleteById(int id);
}
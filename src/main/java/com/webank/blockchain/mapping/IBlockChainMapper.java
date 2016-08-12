package com.webank.blockchain.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.webank.blockchain.domain.TB_Block;

public interface IBlockChainMapper {
 
    //使用@Insert注解指明add方法要执行的SQL
    @Insert("insert into tb_block(body) values(#{body})")
    public int addBlock(TB_Block tb_block);
    
    //使用@Update注解指明update方法要执行的SQL
    @Update("update tb_block set body=#{body} where id = #{id}")
    public int updateBlock(TB_Block block);
      
    @Select("select * from tb_block where id = #{id}")
    public TB_Block selectByIndex(int id);
    
    @Select("select * from tb_block order by id")
    public List<TB_Block> selectAll();
}
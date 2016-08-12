package com.webank.blockchain.mapping;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.webank.blockchain.domain.Block;

public interface IBlockChainMapper {
 
    //使用@Insert注解指明add方法要执行的SQL
    @Insert("insert into block(block_index,prevHash,prevIndex,hash) values(#{index},#{prevHash},#{prevIndex},#{hash})")
    public int addBlock(Block block);
     
    //使用@Update注解指明update方法要执行的SQL
    @Update("update block set hash=#{hash} where block_index = #{index}")
    public int updateBlock(Block block);
      
    @Select("select * from block where block_index = #{index}")
    public Block selectByIndex(int block_index);
}

package com.kcy.system.dao;

import com.kcy.system.model.MillionType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MillionTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionType record);

    int insertSelective(MillionType record);

    MillionType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionType record);

    int updateByPrimaryKey(MillionType record);
}
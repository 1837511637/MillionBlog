package com.kcy.system.dao;

import com.kcy.system.model.MillionLabel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MillionLabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionLabel record);

    int insertSelective(MillionLabel record);

    MillionLabel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionLabel record);

    int updateByPrimaryKey(MillionLabel record);
}
package com.kcy.system.dao;

import com.kcy.system.model.MillionUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MillionUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionUser record);

    int insertSelective(MillionUser record);

    MillionUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionUser record);

    int updateByPrimaryKey(MillionUser record);
}
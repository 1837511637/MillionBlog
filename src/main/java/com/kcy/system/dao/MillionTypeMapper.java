package com.kcy.system.dao;

import com.kcy.common.base.BaseDaoImpl;
import com.kcy.system.model.MillionType;

public interface MillionTypeMapper extends BaseDaoImpl<MillionType> {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionType record);

    int insertSelective(MillionType record);

    MillionType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionType record);

    int updateByPrimaryKey(MillionType record);
}
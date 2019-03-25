package com.kcy.system.dao;

import com.kcy.common.base.BaseDao;
import com.kcy.system.model.MillionType;

public interface MillionTypeMapper extends BaseDao<MillionType> {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionType record);

    int insertSelective(MillionType record);

    MillionType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionType record);

    int updateByPrimaryKey(MillionType record);
}
package com.kcy.system.dao;

import com.kcy.common.base.BaseDaoImpl;
import com.kcy.system.model.MillionUser;

public interface MillionUserMapper extends BaseDaoImpl<MillionUser> {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionUser record);

    int insertSelective(MillionUser record);

    MillionUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionUser record);

    int updateByPrimaryKey(MillionUser record);
}
package com.kcy.system.dao;

import com.kcy.common.base.BaseDao;
import com.kcy.system.model.MillionUser;

public interface MillionUserMapper extends BaseDao<MillionUser> {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionUser record);

    int insertSelective(MillionUser record);

    MillionUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionUser record);

    int updateByPrimaryKey(MillionUser record);
}
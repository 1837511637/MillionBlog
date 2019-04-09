package com.kcy.system.dao;

import com.kcy.common.base.BaseDao;
import com.kcy.system.model.MillionUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MillionUserMapper extends BaseDao<MillionUser> {
}
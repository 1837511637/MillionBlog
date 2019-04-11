package com.kcy.system.dao;

import com.kcy.common.base.BaseDao;
import com.kcy.system.model.MillionUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MillionUserMapper extends BaseDao<MillionUser> {
    Integer count(Map<String, Object> param);
}
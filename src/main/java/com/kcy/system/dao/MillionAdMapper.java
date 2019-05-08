package com.kcy.system.dao;

import com.kcy.common.base.BaseDao;
import com.kcy.system.model.MillionAd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MillionAdMapper extends BaseDao<MillionAd> {
}
package com.kcy.system.dao;

import com.kcy.common.base.BaseDao;
import com.kcy.system.model.MillionBlog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MillionBlogMapper extends BaseDao<MillionBlog> {
    List<MillionBlog> theLatestData(Map<String, Object> param);
}
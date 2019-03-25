package com.kcy.system.dao;

import com.kcy.common.base.BaseDao;
import com.kcy.system.model.MillionBlog;

public interface MillionBlogMapper extends BaseDao<MillionBlog> {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionBlog record);

    int insertSelective(MillionBlog record);

    MillionBlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionBlog record);

    int updateByPrimaryKey(MillionBlog record);
}
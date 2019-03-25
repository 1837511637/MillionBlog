package com.kcy.system.dao;

import com.kcy.common.base.BaseDao;
import com.kcy.system.model.MillionAssociation;

public interface MillionAssociationMapper extends BaseDao<MillionAssociation> {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionAssociation record);

    int insertSelective(MillionAssociation record);

    MillionAssociation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionAssociation record);

    int updateByPrimaryKey(MillionAssociation record);
}
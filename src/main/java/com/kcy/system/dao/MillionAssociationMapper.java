package com.kcy.system.dao;

import com.kcy.common.base.BaseDaoImpl;
import com.kcy.system.model.MillionAssociation;

public interface MillionAssociationMapper extends BaseDaoImpl<MillionAssociation> {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionAssociation record);

    int insertSelective(MillionAssociation record);

    MillionAssociation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionAssociation record);

    int updateByPrimaryKey(MillionAssociation record);
}
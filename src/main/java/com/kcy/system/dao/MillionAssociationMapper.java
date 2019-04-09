package com.kcy.system.dao;

import com.kcy.system.model.MillionAssociation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MillionAssociationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionAssociation record);

    int insertSelective(MillionAssociation record);

    MillionAssociation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionAssociation record);

    int updateByPrimaryKey(MillionAssociation record);
}
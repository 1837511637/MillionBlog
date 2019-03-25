package com.kcy.system.dao;

import com.kcy.common.base.BaseDaoImpl;
import com.kcy.system.model.MillionLabel;

public interface MillionLabelMapper extends BaseDaoImpl<MillionLabel> {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionLabel record);

    int insertSelective(MillionLabel record);

    MillionLabel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionLabel record);

    int updateByPrimaryKey(MillionLabel record);
}
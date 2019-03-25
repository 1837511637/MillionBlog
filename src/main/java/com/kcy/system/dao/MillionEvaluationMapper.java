package com.kcy.system.dao;

import com.kcy.common.base.BaseDaoImpl;
import com.kcy.system.model.MillionEvaluation;

public interface MillionEvaluationMapper extends BaseDaoImpl<MillionEvaluation> {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionEvaluation record);

    int insertSelective(MillionEvaluation record);

    MillionEvaluation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionEvaluation record);

    int updateByPrimaryKey(MillionEvaluation record);
}
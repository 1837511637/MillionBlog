package com.kcy.system.dao;

import com.kcy.common.base.BaseDao;
import com.kcy.system.model.MillionEvaluation;

public interface MillionEvaluationMapper extends BaseDao<MillionEvaluation> {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionEvaluation record);

    int insertSelective(MillionEvaluation record);

    MillionEvaluation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionEvaluation record);

    int updateByPrimaryKey(MillionEvaluation record);
}
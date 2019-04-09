package com.kcy.system.dao;

import com.kcy.system.model.MillionEvaluation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MillionEvaluationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MillionEvaluation record);

    int insertSelective(MillionEvaluation record);

    MillionEvaluation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MillionEvaluation record);

    int updateByPrimaryKey(MillionEvaluation record);
}
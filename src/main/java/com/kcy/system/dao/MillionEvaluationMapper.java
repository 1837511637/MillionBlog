package com.kcy.system.dao;
import com.kcy.common.base.BaseDao;
import com.kcy.system.model.MillionEvaluation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MillionEvaluationMapper extends BaseDao<MillionEvaluation> {
    List<MillionEvaluation> theLatestData(Map<String, Object> param);
}

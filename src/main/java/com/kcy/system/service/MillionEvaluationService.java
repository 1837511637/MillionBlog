package com.kcy.system.service;

import com.kcy.common.model.ResponseWrapper;
import com.kcy.system.model.MillionEvaluation;
import com.kcy.system.vo.VoEvaluate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface MillionEvaluationService {
    ResponseWrapper getAllByAimsId(Map<String, Object> param);

    List<VoEvaluate> recursiveQuery(List <MillionEvaluation> millionEvaluations, Integer id);

    ResponseWrapper commentMessage(HttpServletRequest request, MillionEvaluation millionEvaluation);
}

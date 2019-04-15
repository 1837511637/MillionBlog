package com.kcy.system.controller;

import com.kcy.common.model.ResponseUtils;
import com.kcy.common.model.ResponseWrapper;
import com.kcy.system.model.MillionEvaluation;
import com.kcy.system.service.MillionEvaluationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/eval")
public class MillionEvaluateController {

    @Autowired
    private MillionEvaluationService millionEvaluationService;

    @PostMapping("/commentMessage")
    @ApiOperation(value = "编写评论", notes = "评论/轻语评论/留言")
    public ResponseWrapper commentMessage(HttpServletRequest request, MillionEvaluation millionEvaluation) {
        return millionEvaluationService.commentMessage(request, millionEvaluation);
    }
}

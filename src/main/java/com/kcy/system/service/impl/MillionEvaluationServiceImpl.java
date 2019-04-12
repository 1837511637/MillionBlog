package com.kcy.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kcy.common.model.PageConst;
import com.kcy.common.model.ResponseUtils;
import com.kcy.common.model.ResponseWrapper;
import com.kcy.common.utils.DateUtils;
import com.kcy.common.utils.Misc;
import com.kcy.system.dao.MillionEvaluationMapper;
import com.kcy.system.model.MillionEvaluation;
import com.kcy.system.service.MillionEvaluationService;
import com.kcy.system.vo.VoEvaluate;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log
@Service
public class MillionEvaluationServiceImpl implements MillionEvaluationService {

    @Autowired
    private MillionEvaluationMapper millionEvaluationMapper;

    //获取评论
    public ResponseWrapper getAllByAimsId(Map<String, Object> map) {
        //Integer aimsid = Misc.parseInteger(Misc.getString(map.get("id")));
        int pageNo = Misc.parseInteger(Misc.getString(map.get("page")));
        if(pageNo < 1) {
            pageNo = 1;
        }
        ResponseWrapper responseWrapper = ResponseUtils.successResponse("");
        /*Map<String, Object> param = new HashMap();
        param.put("blogid", aimsid);
        param.put("evaluation", 0);
        param.put("type", 1);*/
        Page <Object> page = PageHelper.startPage(pageNo, PageConst.rows);
        List <MillionEvaluation> millionEvaluations = millionEvaluationMapper.findAll(map);
        responseWrapper.setPage(pageNo);
        responseWrapper.setTotalpage(page.getPages());
        List<Integer> ids = new ArrayList();
        List<VoEvaluate> evaluates = new ArrayList();
        for(MillionEvaluation millionEvaluation : millionEvaluations) {
            VoEvaluate voEvaluate = new VoEvaluate();
            voEvaluate.setEvalid(millionEvaluation.getId());
            voEvaluate.setContent(millionEvaluation.getContent());
            voEvaluate.setCreatetime(DateUtils.dateFormat2(millionEvaluation.getCreatetime()));
            voEvaluate.setHeaimg(millionEvaluation.getHeadimg());
            voEvaluate.setIschild(millionEvaluation.getIschild());
            voEvaluate.setUsername(millionEvaluation.getName());
            voEvaluate.setWebLink(millionEvaluation.getWeblink());
            evaluates.add(voEvaluate);
            ids.add(millionEvaluation.getId());
        }

        if(ids.size() > 0) {
            List <MillionEvaluation> millionEvaluations1 = millionEvaluationMapper.selectEvaluateForIn(ids);
            for(VoEvaluate voEvaluate : evaluates) {
                //如果该评论有回复数据
                if("1".equals(voEvaluate)) {
                    voEvaluate.setVoEvaluateList(recursiveQuery(millionEvaluations1, voEvaluate.getEvalid()));
                }
            }
        }

        responseWrapper.addAttribute("datas", evaluates);

        return responseWrapper;
    }

    //递归插入评论
    public List<VoEvaluate> recursiveQuery(List <MillionEvaluation> millionEvaluations, Integer id) {
        List<VoEvaluate> voEvaluates = new ArrayList();
        for(MillionEvaluation millionEvaluation : millionEvaluations) {
            //如果评论的父评论id == id
            if(millionEvaluation.getEvaluation().equals(id)) {
                VoEvaluate voEvaluate = new VoEvaluate();
                voEvaluate.setEvalid(millionEvaluation.getId());
                voEvaluate.setContent(millionEvaluation.getContent());
                voEvaluate.setCreatetime(DateUtils.dateFormat2(millionEvaluation.getCreatetime()));
                voEvaluate.setHeaimg(millionEvaluation.getHeadimg());
                voEvaluate.setIschild(millionEvaluation.getIschild());
                voEvaluate.setUsername(millionEvaluation.getName());
                voEvaluate.setWebLink(millionEvaluation.getWeblink());
                //如果该评论有回复数据    (减少递归次数)
                if("1".equals(voEvaluate)) {
                    voEvaluate.setVoEvaluateList(recursiveQuery(millionEvaluations, voEvaluate.getEvalid()));
                }
                voEvaluates.add(voEvaluate);
                //添加完则在子评论列表中删除 (减少循环次数)
                millionEvaluations.remove(millionEvaluation);
            }
        }
        return voEvaluates;
    }
}

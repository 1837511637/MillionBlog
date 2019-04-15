package com.kcy.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kcy.common.constant.RedisConst;
import com.kcy.common.constant.WebConst;
import com.kcy.common.model.PageConst;
import com.kcy.common.model.ResponseUtils;
import com.kcy.common.model.ResponseWrapper;
import com.kcy.common.redis.RedisComponent;
import com.kcy.common.utils.BlogUtils;
import com.kcy.common.utils.DateUtils;
import com.kcy.common.utils.IPUtils;
import com.kcy.common.utils.Misc;
import com.kcy.system.dao.MillionBlogMapper;
import com.kcy.system.dao.MillionEvaluationMapper;
import com.kcy.system.model.MillionBlog;
import com.kcy.system.model.MillionEvaluation;
import com.kcy.system.service.MillionEvaluationService;
import com.kcy.system.vo.VoEvaluate;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

@Log
@Service
public class MillionEvaluationServiceImpl implements MillionEvaluationService {

    @Autowired
    private MillionEvaluationMapper millionEvaluationMapper;
    @Autowired
    private MillionBlogMapper millionBlogMapper;
    @Autowired
    private RedisComponent redisComponent;

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

    //评论留言
    public ResponseWrapper commentMessage(HttpServletRequest request, MillionEvaluation millionEvaluation) {
        if(millionEvaluation == null) {
            return ResponseUtils.errorResponse("网络繁忙，请刷新");
        }
        if(Misc.isStringEmpty(millionEvaluation.getContent())) {
            return ResponseUtils.errorResponse("评论内容不能为空!");
        }
        if(Misc.getStringLength(millionEvaluation.getContent(), "ISO-8859-1") > WebConst.MAX_TEXT_EVALUATE) {
            return ResponseUtils.errorResponse("评论字数超过上限");
        }
        if(!Misc.isValidEmail(millionEvaluation.getEmail())) {
            return ResponseUtils.errorResponse("邮箱格式不正确");
        }
        if(Misc.isIllegal(millionEvaluation.getContent())) {
            return ResponseUtils.errorResponse("内容非法");
        }
        if(Misc.isStringEmpty(millionEvaluation.getName())) {
            return ResponseUtils.errorResponse("名称不能为空");
        }

        MillionBlog millionBlog = millionBlogMapper.selectByPrimaryKey(millionEvaluation.getBlogid());
        if(millionBlog == null || !"1".equals(millionBlog.getStatus())) {
            return ResponseUtils.errorResponse("博客已下线");
        }

        if(!"1".equals(millionBlog.getIseval())) {
            return ResponseUtils.errorResponse("该条博客禁止评论");
        }

        if(Misc.isWebLink(millionEvaluation.getWeblink())) {
            String icon = BlogUtils.getIcon(millionEvaluation.getWeblink());
            if(!Misc.isStringEmpty(icon)) {
                millionEvaluation.setHeadimg(icon);
            }
        }

        String ipAddrByRequest = IPUtils.getIpAddrByRequest(request);
        millionEvaluation.setIp(ipAddrByRequest);
        millionEvaluation.setStatus("1");
        millionEvaluation.setCreatetime(new Date());
        millionEvaluation.setIschild("0");

        //判断是否是第一条评论
        if(millionEvaluation.getEvaluation() != 0) {
            //如果不是就查询上一条评论
            MillionEvaluation millionEvaluation1 = millionEvaluationMapper.selectByPrimaryKey(millionEvaluation.getEvaluation());
            //判断上一条数据是否有子评论
            if(millionEvaluation1.getIschild().equals("0")) {
                //没有的话就更新
                millionEvaluation1.setIschild("1");
                millionEvaluationMapper.updateByPrimaryKeySelective(millionEvaluation1);
            }
            //判断上一条是否是第一条评论
            millionEvaluation.setFirstevalid(millionEvaluation1.getFirstevalid() == 0 ? millionEvaluation1.getId() : millionEvaluation1.getFirstevalid());
        } else {
            //如果是第一条评论
            millionEvaluation.setFirstevalid(0);
            if("3".equals(millionEvaluation.getType())) {
                redisComponent.delete(RedisConst.MENU_EVAL);
            }
        }

        millionEvaluationMapper.insertSelective(millionEvaluation);
        log.info(millionEvaluation.toString());

        millionBlog.setEvalnum(millionBlog.getEvalnum() + 1);
        millionBlogMapper.updateByPrimaryKeySelective(millionBlog);

        return ResponseUtils.successResponse("评论成功");
    }
}

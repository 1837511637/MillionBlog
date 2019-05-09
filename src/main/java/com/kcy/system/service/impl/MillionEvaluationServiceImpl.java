package com.kcy.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kcy.common.constant.RedisConst;
import com.kcy.common.constant.WebConst;
import com.kcy.common.model.PageConst;
import com.kcy.common.model.ResponseUtils;
import com.kcy.common.model.ResponseWrapper;
import com.kcy.common.redis.RedisService;
import com.kcy.common.utils.BlogUtils;
import com.kcy.common.utils.DateUtils;
import com.kcy.common.utils.IPUtils;
import com.kcy.common.utils.Misc;
import com.kcy.system.dao.MillionBlogMapper;
import com.kcy.system.dao.MillionEvaluationMapper;
import com.kcy.system.model.MillionBlog;
import com.kcy.system.model.MillionEvaluation;
import com.kcy.system.model.MillionUser;
import com.kcy.system.service.MillionEvaluationService;
import com.kcy.system.vo.VoEvaluate;
import com.kcy.system.vo.VoEvaluateMsg;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Log
@Service
public class MillionEvaluationServiceImpl implements MillionEvaluationService {

    @Autowired
    private MillionEvaluationMapper millionEvaluationMapper;
    @Autowired
    private MillionBlogMapper millionBlogMapper;
    @Autowired
    private RedisService redisService;

    //获取评论
    @Override
    public ResponseWrapper getAllByAimsId(Map<String, Object> map) {
        //Integer aimsid = Misc.parseInteger(Misc.getString(map.get("id")));
        Integer pageNo = Misc.getIntegerPage(Misc.getString(map.get("page")));
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
            VoEvaluate voEvaluate = getVoEvaluate(millionEvaluation);
            evaluates.add(voEvaluate);
            ids.add(millionEvaluation.getId());
        }

        if(ids.size() <= 0) {
            return ResponseUtils.successResponse("datas", evaluates, "");
        }

        //查询所有首列评论的子评论
        List <MillionEvaluation> millionEvaluations1 = millionEvaluationMapper.selectEvaluateForIn(ids);
        LinkedHashMap<Integer, List<VoEvaluate>> linkedHashMap = new LinkedHashMap(10);
        for(MillionEvaluation millionEvaluation : millionEvaluations1) {
            VoEvaluate voEvaluate = getVoEvaluate(millionEvaluation);
            Integer firstevalid = millionEvaluation.getFirstevalid();
            List <VoEvaluate> voEvaluates = linkedHashMap.get(firstevalid);
            if(voEvaluates == null) {
                voEvaluates = new ArrayList();
                voEvaluates.add(voEvaluate);
                linkedHashMap.put(firstevalid, voEvaluates);
            } else {
                voEvaluates.add(voEvaluate);
            }
        }

        //分类
        for(Integer id : linkedHashMap.keySet()) {
            for(VoEvaluate voEvaluate : evaluates) {
                if(voEvaluate.getEvalid().equals(id)) {
                    voEvaluate.setVoEvaluateList(linkedHashMap.get(id));
                }
            }
        }

        responseWrapper.addAttribute("datas", evaluates);

        return responseWrapper;
    }

    //类型转换
    public VoEvaluate getVoEvaluate(MillionEvaluation millionEvaluation) {
        VoEvaluate voEvaluate = new VoEvaluate();
        voEvaluate.setEvalid(millionEvaluation.getId());
        voEvaluate.setContent(millionEvaluation.getContent());
        voEvaluate.setCreatetime(DateUtils.dateFormat2(millionEvaluation.getCreatetime()));
        voEvaluate.setHeaimg(BlogUtils.getDefaultHeadimg(millionEvaluation.getHeadimg()));
        voEvaluate.setUsername(millionEvaluation.getName());
        voEvaluate.setWebLink(millionEvaluation.getWeblink());
        voEvaluate.setReplyid(millionEvaluation.getReplyid());
        voEvaluate.setReplyname(millionEvaluation.getReplyname());
        voEvaluate.setReplyweblink(millionEvaluation.getReplyweblink());
        voEvaluate.setIsuser(millionEvaluation.getIsuser());
        return voEvaluate;
    }

    //递归查询评论
    /*public List<VoEvaluate> recursiveQuery(List <MillionEvaluation> millionEvaluations, Integer id) {
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
                if("1".equals(voEvaluate.getIschild())) {
                    voEvaluate.setVoEvaluateList(recursiveQuery(millionEvaluations, voEvaluate.getEvalid()));
                }
                voEvaluates.add(voEvaluate);
                //添加完则在子评论列表中删除 (减少循环次数)
                //millionEvaluations.remove(millionEvaluation);
            }
        }
        return voEvaluates;
    }*/

    //评论留言
    @Override
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

        if(Misc.isIllegal(millionEvaluation.getContent())) {
            return ResponseUtils.errorResponse("内容非法");
        }

        if(!BlogUtils.isHttpOrHttps(millionEvaluation.getWeblink())) {
            return ResponseUtils.errorResponse("http://忘记输入了哦");
        }

        //判断网站是否失效
        if(!BlogUtils.getLinkInvalid(millionEvaluation.getWeblink())) {
            millionEvaluation.setWeblink("");
        }

        MillionBlog millionBlog = null;
        //判断是评论还是留言
        if("1".equals(millionEvaluation.getType()) || "2".equals(millionEvaluation.getType())) {
            millionBlog = millionBlogMapper.selectByPrimaryKey(millionEvaluation.getBlogid());
            if(!"1".equals(millionBlog.getIseval())) {
                return ResponseUtils.errorResponse("该条博客禁止评论");
            }
            if(millionBlog == null || !"1".equals(millionBlog.getStatus())) {
                return ResponseUtils.errorResponse("博客已下线");
            }
        }

        //判断是否是作者
        MillionUser loginUser = BlogUtils.getLoginUser(request);
        if(loginUser == null) {
            if(!Misc.isValidEmail(millionEvaluation.getEmail())) {
                return ResponseUtils.errorResponse("邮箱格式不正确");
            }

            if(Misc.isStringEmpty(millionEvaluation.getName())) {
                return ResponseUtils.errorResponse("名称不能为空");
            }
            millionEvaluation.setIsuser("0");
            //设置ico为头像
            String icon = BlogUtils.getIcon(millionEvaluation.getWeblink());
            if(!Misc.isStringEmpty(icon)) {
                millionEvaluation.setHeadimg(icon);
            }
        } else {
            millionEvaluation = authorReview(loginUser, millionEvaluation);
        }

        String ipAddrByRequest = IPUtils.getIpAddrByRequest(request);
        millionEvaluation.setIp(ipAddrByRequest);
        millionEvaluation.setStatus("1");
        millionEvaluation.setCreatetime(new Date());

        if(millionEvaluation.getReplyid() !=null && millionEvaluation.getReplyid() > 0) {
            MillionEvaluation replyEvaluation = millionEvaluationMapper.selectByPrimaryKey(millionEvaluation.getReplyid());
            millionEvaluation.setReplyname(replyEvaluation.getName());
            millionEvaluation.setReplyweblink(replyEvaluation.getWeblink());
        }

        //判断是否是留言
        if("3".equals(millionEvaluation.getType())) {
            //清除缓存
            redisService.remove(RedisConst.MENU_EVAL);
        }

        millionEvaluationMapper.insertSelective(millionEvaluation);
        //log.info(millionEvaluation.toString());

        //增加评论量
        if(millionBlog != null) {
            millionBlog.setEvalnum(millionBlog.getEvalnum() + 1);
            millionBlogMapper.updateByPrimaryKeySelective(millionBlog);
        }

        //使用redis存储评论者信息
        VoEvaluateMsg voEvaluateMsg = (VoEvaluateMsg)redisService.get(RedisConst.EVALUATE_MSG.replace("IP", millionEvaluation.getIp()));
        if(voEvaluateMsg == null) {
            voEvaluateMsg = new VoEvaluateMsg();
            voEvaluateMsg.setName(millionEvaluation.getName());
            voEvaluateMsg.setEmail(millionEvaluation.getEmail());
            voEvaluateMsg.setWeblink(millionEvaluation.getWeblink());
            redisService.set(RedisConst.EVALUATE_MSG.replace("IP", millionEvaluation.getIp()), voEvaluateMsg, WebConst.evaluateMessageTime.longValue());
        }

        return ResponseUtils.successResponse("评论成功");
    }

    //作者评论
    public MillionEvaluation authorReview(MillionUser loginUser, MillionEvaluation millionEvaluation) {
        millionEvaluation.setIsuser("1");
        millionEvaluation.setHeadimg(loginUser.getHeadimg());
        millionEvaluation.setName(loginUser.getName());
        millionEvaluation.setEmail(loginUser.getEmail());
        millionEvaluation.setWeblink(loginUser.getWeblink());
        return millionEvaluation;
    }
}

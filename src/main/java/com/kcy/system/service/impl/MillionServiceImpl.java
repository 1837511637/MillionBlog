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
import com.kcy.system.dao.*;
import com.kcy.system.model.*;
import com.kcy.system.service.MillionBlogService;
import com.kcy.system.service.MillionEvaluationService;
import com.kcy.system.service.MillionService;
import com.kcy.system.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class MillionServiceImpl implements MillionService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private MillionBlogService millionBlogService;
    @Autowired
    private MillionWhisperMapper millionWhisperMapper;
    @Autowired
    private MillionBlogMapper millionBlogMapper;
    @Autowired
    private MillionEvaluationService millionEvaluationService;
    @Autowired
    private MillionEvaluationMapper millionEvaluationMapper;
    @Autowired
    private MillionTypeMapper millionTypeMapper;
    @Autowired
    private MillionLabelMapper millionLabelMapper;

    //获取首页信息数据
    @Override
    public ResponseWrapper getIndexData() {
        ResponseWrapper responseWrapper = (ResponseWrapper)redisService.get(RedisConst.INDEX_RESPONSEWRAPPER);
        if(responseWrapper == null) {
            Map<String, Object> param = new HashMap();
            param.put("page", 1);
            responseWrapper = millionBlogService.findAll(param);
            if(responseWrapper == null || !responseWrapper.isSuccess()) {
                responseWrapper = ResponseUtils.successResponse("");
            }
            MillionWhisper newsMessage = millionWhisperMapper.getNewsMessage();
            if(newsMessage != null) {
                VoIndexMillionWhisper voIndexMillionWhisper = new VoIndexMillionWhisper();
                voIndexMillionWhisper.setId(newsMessage.getId());
                voIndexMillionWhisper.setContent(newsMessage.getContent());
                responseWrapper.addAttribute("whisper", voIndexMillionWhisper);
            }
            redisService.set(RedisConst.INDEX_RESPONSEWRAPPER, responseWrapper);
        }
        return responseWrapper;
    }

    //文章详情
    @Override
    public ResponseWrapper getBlogDetails(HttpServletRequest request, Integer id) {
        MillionBlog millionBlog = millionBlogMapper.selectByPrimaryKey(id);
        if(millionBlog == null || !"1".equals(millionBlog.getStatus())) {
            return ResponseUtils.errorResponse("此博客已下线");
        }
        //控制文章阅读量
        boolean fale = BlogUtils.checkHitsFrequency(request, id, redisService);
        if(!fale) {
            millionBlog.setViewnum(millionBlog.getViewnum() + 1);
            millionBlogMapper.updateByPrimaryKeySelective(millionBlog);
        }
        VoBlogDetails voBlogDetails = new VoBlogDetails();
        voBlogDetails.setId(millionBlog.getId());
        voBlogDetails.setTitle(millionBlog.getTitle());
        voBlogDetails.setCreatetime(DateUtils.formatDate(millionBlog.getCreatetime()));
        voBlogDetails.setType(millionBlog.getTypename());
        voBlogDetails.setEvalnum(millionBlog.getEvalnum() + " 条评论");
        voBlogDetails.setViewnum(millionBlog.getViewnum() + " 次阅读");
        voBlogDetails.setContent(millionBlog.getContent());
        voBlogDetails.setIseval(millionBlog.getIseval());

        //获取关于这篇文章的第一页评论数据
        Map<String, Object> param = new HashMap(3);
        param.put("page", 1);
        param.put("blogid", id);
        param.put("type", 1);
        param.put("firstevalid", 0);
        ResponseWrapper responseWrapper = millionEvaluationService.getAllByAimsId(param);
        if(responseWrapper == null || !responseWrapper.isSuccess()) {
            responseWrapper = ResponseUtils.successResponse("");
        }
        responseWrapper.addAttribute("blog", voBlogDetails);

        return responseWrapper;
    }

    //获取归档数据
    @Override
    public ResponseWrapper getArchives() {
        LinkedHashMap<String, List<VoArchivesBlog>> linkedHashMap = (LinkedHashMap<String, List<VoArchivesBlog>>)redisService.get(RedisConst.ARCHIVES_BLOG);
        if(linkedHashMap == null) {
            List <MillionBlog> millionBlogs = millionBlogMapper.findAll(null);
            linkedHashMap = new LinkedHashMap();
            for(MillionBlog millionBlog : millionBlogs) {
                VoArchivesBlog voArchivesBlog = new VoArchivesBlog();
                voArchivesBlog.setId(millionBlog.getId());
                voArchivesBlog.setCreatetime(DateUtils.getTimeMonth(millionBlog.getCreatetime()));
                voArchivesBlog.setTitle(millionBlog.getTitle());
                //获取该数据年份
                String timeYear = DateUtils.getTimeYear(millionBlog.getCreatetime());
                List <VoArchivesBlog> list = linkedHashMap.get(timeYear);
                if(list == null) {
                    list = new ArrayList();
                    linkedHashMap.put(timeYear, list);
                    list.add(voArchivesBlog);
                } else {
                    list.add(voArchivesBlog);
                }
            }
            redisService.set(RedisConst.ARCHIVES_BLOG, linkedHashMap);
        }
        return ResponseUtils.successResponse("datas", linkedHashMap, "");
    }

    @Override
    public ResponseWrapper getGuestbooks(Integer page) {
        Map<String, Object> param = new HashMap(6);
        param.put("page", page);
        param.put("type", "3");
        param.put("firstevalid", 0);
        ResponseWrapper responseWrapper = millionEvaluationService.getAllByAimsId(param);
        if(!responseWrapper.isSuccess()) {
            responseWrapper = ResponseUtils.successResponse("data", new ArrayList<VoEvaluate>(), "");
        }
        return responseWrapper;
    }

    /**获取全局数据**/
    @Override
    public ResponseWrapper getMenuDatas(HttpServletRequest request) {
        ResponseWrapper responseWrapper = ResponseUtils.successResponse("");
        Map<String, Object> param = new HashMap();
        param.put("limit", 5);
        //获取热门博客
        List<VoMenuBlog> voMenuBlogs = (List<VoMenuBlog>)redisService.get(RedisConst.MENU_BLOG);
        if(voMenuBlogs == null) {
            voMenuBlogs = new ArrayList();
            List <MillionBlog> millionBlogs = millionBlogMapper.theLatestData(param);
            for(MillionBlog millionBlog : millionBlogs) {
                VoMenuBlog voMenuBlog = new VoMenuBlog();
                voMenuBlog.setId(millionBlog.getId());
                voMenuBlog.setTitle(millionBlog.getTitle());
                voMenuBlogs.add(voMenuBlog);
            }
            redisService.set(RedisConst.MENU_BLOG, voMenuBlogs);
        }
        responseWrapper.addAttribute("menuBlogs", voMenuBlogs);
        //最新留言
        List<VoMenuEvaluate> voMenuEvaluates = (List<VoMenuEvaluate>)redisService.get(RedisConst.MENU_EVAL);
        if(voMenuEvaluates == null) {
            voMenuEvaluates = new ArrayList();
            param.put("type", "3");
            List <MillionEvaluation> millionEvaluations = millionEvaluationMapper.theLatestData(param);
            for(MillionEvaluation millionEvaluation : millionEvaluations) {
                VoMenuEvaluate voMenuEvaluate = new VoMenuEvaluate();
                voMenuEvaluate.setName(millionEvaluation.getName());
                voMenuEvaluate.setContent(millionEvaluation.getContent());
                voMenuEvaluate.setWeblink(millionEvaluation.getWeblink());
                voMenuEvaluates.add(voMenuEvaluate);
            }
            redisService.set(RedisConst.MENU_EVAL, voMenuEvaluates);
        }
        responseWrapper.addAttribute("menuEvals", voMenuEvaluates);
        //标签
        List<VoMenuLabel> voMenuLabels = (List<VoMenuLabel>)redisService.get(RedisConst.MENU_LABEL);
        if(voMenuLabels == null) {
            voMenuLabels = new ArrayList();
            List<MillionLabel> millionLabels = millionLabelMapper.findAll(null);
            for(MillionLabel millionLabel : millionLabels) {
                VoMenuLabel voMenuLabel = new VoMenuLabel();
                voMenuLabel.setId(millionLabel.getId());
                voMenuLabel.setName(millionLabel.getName());
                voMenuLabels.add(voMenuLabel);
            }
            redisService.set(RedisConst.MENU_LABEL, voMenuLabels);
        }
        responseWrapper.addAttribute("menuLabels", voMenuLabels);
        //类型
        List<VoHeadType> voHeadTypes = (List<VoHeadType>)redisService.get(RedisConst.HEAD_TYPE);
        if(voHeadTypes == null) {
            voHeadTypes = new ArrayList();
            List <MillionType> millionTypes = millionTypeMapper.findAll(null);
            for(MillionType millionType : millionTypes) {
                VoHeadType voHeadType = new VoHeadType();
                voHeadType.setId(millionType.getId());
                voHeadType.setName(millionType.getName());
                voHeadTypes.add(voHeadType);
            }
            redisService.set(RedisConst.HEAD_TYPE, voHeadTypes);
        }
        responseWrapper.addAttribute("headTypes", voHeadTypes);

        responseWrapper.addAttribute("isLogin", BlogUtils.isLogin(request));
        return responseWrapper;
    }

    /**获取评论者信息数据**/
    @Override
    public ResponseWrapper getEvalMsg(HttpServletRequest request) {
        String ip = IPUtils.getIpAddrByRequest(request);
        VoEvaluateMsg voEvaluateMsg = (VoEvaluateMsg)redisService.get(RedisConst.EVALUATE_MSG.replace("IP", ip));
        if(voEvaluateMsg == null) {
            voEvaluateMsg = new VoEvaluateMsg();
            voEvaluateMsg.setWeblink("");
            voEvaluateMsg.setEmail("");
            voEvaluateMsg.setName("");
        }
        return ResponseUtils.successResponse("data", voEvaluateMsg, "");
    }

    @Override
    public ResponseWrapper query(Map <String, Object> map) {
        String keyword = Misc.getString(map.get("keyword"));
        Integer typeid = Misc.parseInteger(Misc.getString(map.get("typeid")));
        String labelid = Misc.getString(map.get("labelid"));
        Integer pageNo = Misc.getIntegerPage(Misc.getString(map.get("page")));
        Map<String, Object> param = new HashMap();
        if(!Misc.isStringEmpty(keyword)) {
            param.put("keyword", keyword);
        }
        if(typeid != null && typeid != 0) {
            param.put("typeid", typeid);
        }
        if(!Misc.isStringEmpty(labelid)) {
            param.put("labelid", ","+labelid+",");
        }
        param.put("page", pageNo);
        ResponseWrapper responseWrapper = millionBlogService.findAll(param);
        responseWrapper.addAttribute("query", param);
        return responseWrapper;
    }

    @Override
    public ResponseWrapper getWhisperDatas(Integer pageNo) {
        if(pageNo <=1) {
            pageNo = 1;
        }
        ResponseWrapper responseWrapper = ResponseUtils.successResponse("");
        Page <Object> page = PageHelper.startPage(pageNo, PageConst.rows);
        List <MillionWhisper> millionWhispers = millionWhisperMapper.findAll(null);
        responseWrapper.setPage(pageNo);
        responseWrapper.setTotalpage(page.getPages());
        List<Integer> ids = new ArrayList();
        LinkedHashMap<Integer, VoWhispers> voWhispersLinkedHashMap = new LinkedHashMap();
        for(MillionWhisper millionWhisper : millionWhispers) {
            ids.add(millionWhisper.getId());
            VoWhispers voWhispers = new VoWhispers();
            voWhispers.setId(millionWhisper.getId());
            voWhispers.setHeadimg(millionWhisper.getHeadimg());
            voWhispers.setUsername(millionWhisper.getUsername());
            voWhispers.setCreatetime(DateUtils.formatDate(millionWhisper.getCreatetime()));
            voWhispers.setContent(millionWhisper.getContent());
            voWhispers.setWebLink(WebConst.Link);
            voWhispersLinkedHashMap.put(millionWhisper.getId(), voWhispers);
        }
        Map<String, Object> param = new HashMap();
        param.put("ids", ids.size() == 0 ? null : ids);
        param.put("type", "5");
        List <MillionEvaluation> millionEvaluations = millionEvaluationMapper.findAll(param);
        LinkedHashMap<Integer, List<VoWhisperEvaluate>> voWhisperEvaluateLinkedHashMap = new LinkedHashMap();
        for(MillionEvaluation millionEvaluation : millionEvaluations) {
            VoWhisperEvaluate voWhisperEvaluate = new VoWhisperEvaluate();
            voWhisperEvaluate.setId(millionEvaluation.getId());
            voWhisperEvaluate.setHeadimg(BlogUtils.getDefaultHeadimg(millionEvaluation.getHeadimg()));
            voWhisperEvaluate.setUsername(millionEvaluation.getName());
            voWhisperEvaluate.setContent(millionEvaluation.getContent());
            voWhisperEvaluate.setWebLink(millionEvaluation.getWeblink());
            voWhisperEvaluate.setIsuser(millionEvaluation.getIsuser());
            List <VoWhisperEvaluate> voWhisperEvaluates = voWhisperEvaluateLinkedHashMap.get(millionEvaluation.getBlogid());
            if(voWhisperEvaluates == null) {
                voWhisperEvaluates = new ArrayList();
                voWhisperEvaluates.add(voWhisperEvaluate);
                voWhisperEvaluateLinkedHashMap.put(millionEvaluation.getBlogid(), voWhisperEvaluates);
            } else {
                voWhisperEvaluates.add(voWhisperEvaluate);
            }
        }

        List<VoWhispers> voWhisperses = new ArrayList();
        for(Integer blogid : voWhispersLinkedHashMap.keySet()) {
            VoWhispers voWhispers = voWhispersLinkedHashMap.get(blogid);
            List <VoWhisperEvaluate> voWhisperEvaluates = voWhisperEvaluateLinkedHashMap.get(blogid);
            voWhispers.setVoWhisperEvaluateList(voWhisperEvaluates);
            voWhisperses.add(voWhispers);
        }
        responseWrapper.addAttribute("datas", voWhisperses);

        return responseWrapper;
    }
}

package com.kcy.system.service.impl;

import com.kcy.common.constant.RedisConst;
import com.kcy.common.model.ResponseUtils;
import com.kcy.common.model.ResponseWrapper;
import com.kcy.common.redis.RedisComponent;
import com.kcy.system.dao.MillionWhisperMapper;
import com.kcy.system.model.MillionWhisper;
import com.kcy.system.service.MillionBlogService;
import com.kcy.system.service.MillionService;
import com.kcy.system.vo.VoBlog;
import com.kcy.system.vo.VoIndexMillionWhisper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MillionServiceImpl implements MillionService {

    @Autowired
    private RedisComponent redisComponent;
    @Autowired
    private MillionBlogService millionBlogService;
    @Autowired
    private MillionWhisperMapper millionWhisperMapper;


    //获取首页信息数据
    public ResponseWrapper getIndexData() {
        ResponseWrapper responseWrapper = (ResponseWrapper)redisComponent.getOpsForObject(RedisConst.INDEX_RESPONSEWRAPPER);
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
            redisComponent.opsForValue(RedisConst.INDEX_RESPONSEWRAPPER, responseWrapper);
        }
        return responseWrapper;
    }

    public ResponseWrapper getBlogDetails(Long id) {
        return null;
    }
}

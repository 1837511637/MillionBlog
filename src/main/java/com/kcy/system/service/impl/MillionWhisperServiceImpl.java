package com.kcy.system.service.impl;

import com.kcy.common.constant.RedisConst;
import com.kcy.common.model.ResponseUtils;
import com.kcy.common.model.ResponseWrapper;
import com.kcy.common.redis.RedisService;
import com.kcy.common.utils.BlogUtils;
import com.kcy.common.utils.IPUtils;
import com.kcy.common.utils.Misc;
import com.kcy.system.dao.MillionWhisperMapper;
import com.kcy.system.model.MillionUser;
import com.kcy.system.model.MillionWhisper;
import com.kcy.system.service.MillionWhisperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class MillionWhisperServiceImpl implements MillionWhisperService {

    @Autowired
    private MillionWhisperMapper millionWhisperMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public ResponseWrapper publishWhisper(HttpServletRequest request, MillionWhisper millionWhisper) {
        if(Misc.isStringEmpty(millionWhisper.getContent())) {
            return ResponseUtils.errorResponse("轻语内容不能为空");
        }
        if(Misc.isIllegal(millionWhisper.getContent())) {
            return ResponseUtils.errorResponse("内容非法");
        }
        MillionUser loginUser = BlogUtils.getLoginUser(request);
        if(loginUser == null) {
            return ResponseUtils.errorResponse("未登录");
        }
        millionWhisper.setUserid(loginUser.getId());
        millionWhisper.setUsername(loginUser.getName());
        millionWhisper.setHeadimg(loginUser.getHeadimg());
        millionWhisper.setCreatetime(new Date());
        millionWhisper.setStatus("1");
        millionWhisper.setIp(IPUtils.getIpAddrByRequest(request));
        millionWhisperMapper.insertSelective(millionWhisper);
        redisService.remove(RedisConst.INDEX_RESPONSEWRAPPER);
        return ResponseUtils.successResponse("发表成功");
    }
}

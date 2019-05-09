package com.kcy.system.service.impl;

import com.kcy.common.constant.RedisConst;
import com.kcy.common.constant.WebConst;
import com.kcy.common.model.ResponseUtils;
import com.kcy.common.model.ResponseWrapper;
import com.kcy.common.redis.RedisService;
import com.kcy.common.utils.ConfidentialityUtils;
import com.kcy.common.utils.IPUtils;
import com.kcy.common.utils.Misc;
import com.kcy.system.dao.MillionUserMapper;
import com.kcy.system.model.MillionUser;
import com.kcy.system.service.MillionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MillionUserServiceImpl implements MillionUserService {

    @Autowired
    private MillionUserMapper millionUserMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public ResponseWrapper login(String username, String password, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(Misc.isStringEmpty(username) || Misc.isStringEmpty(password)){
            return ResponseUtils.errorResponse("用户名与密码不能为空");
        }
        Map<String, Object> param = new HashMap(2);
        param.put("username", username);
        Integer count = millionUserMapper.count(param);
        if(count == null || count == 0) {
            return ResponseUtils.errorResponse("用户被吃掉了");
        }
        String psw = ConfidentialityUtils.EncodeByMD5(WebConst.AES_SALT + password);
        param.put("password", psw);
        List <MillionUser> millionUsers = millionUserMapper.findAll(param);
        if(millionUsers.size() != 1) {
            return ResponseUtils.errorResponse("用户名或密码错误",-5);
        }
        MillionUser millionUser = millionUsers.get(0);
        millionUser.setIp(IPUtils.getIpAddrByRequest(request));
        millionUserMapper.updateByPrimaryKeySelective(millionUser);
        millionUser.setPassword("");
        request.getSession().setAttribute(RedisConst.LOGIN_SESSION_KEY, millionUser);

        String code = RedisConst.LOGIN_ERROR_COUNT.replaceAll("IP", IPUtils.getIpAddrByRequest(request));
        redisService.remove(code);
        return ResponseUtils.successResponse("登录成功");
    }

}

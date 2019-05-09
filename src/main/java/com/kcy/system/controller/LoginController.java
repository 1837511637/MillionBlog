package com.kcy.system.controller;

import com.kcy.common.base.BaseController;
import com.kcy.common.constant.RedisConst;
import com.kcy.common.constant.WebConst;
import com.kcy.common.model.ResponseUtils;
import com.kcy.common.model.ResponseWrapper;
import com.kcy.common.redis.RedisService;
import com.kcy.common.utils.IPUtils;
import com.kcy.common.utils.Misc;
import com.kcy.system.model.MillionUser;
import com.kcy.system.service.MillionUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/admin")
@Log
public class LoginController extends BaseController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private MillionUserService millionUserService;

    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping("/signin")
    public ResponseWrapper doLogin(MillionUser millionUser, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String ipAddrByRequest = IPUtils.getIpAddrByRequest(request);
        String code = RedisConst.LOGIN_ERROR_COUNT.replaceAll("IP", ipAddrByRequest);
        Integer count = Misc.parseInteger(Misc.getString(redisService.get(code)));
        if(count < 3) {
            ResponseWrapper responseWrapper = millionUserService.login(millionUser.getName(), millionUser.getPassword(), request);
            if(responseWrapper.getStatus() == -5) {
                count = 0 == count ? 1 : count + 1;
                redisService.set(code, count, WebConst.loginErrorTime.longValue());
            }
            return responseWrapper;
        }
        log.info(ipAddrByRequest + ":输入密码错误超过3次");
        return ResponseUtils.errorResponse("您输入密码已经错误超过3次，请10分钟后尝试");
    }
}

package com.kcy.system.controller;

import com.kcy.common.base.BaseController;
import com.kcy.common.model.ResponseWrapper;
import com.kcy.common.redis.RedisService;
import com.kcy.system.model.MillionWhisper;
import com.kcy.system.service.MillionWhisperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(value = "轻语表操作")
@RequestMapping("/whisper")
public class MillionWhisperController extends BaseController {

    @Autowired
    private MillionWhisperService millionWhisperService;


    @PostMapping("/publishWhisper")
    @ApiOperation(value = "编写轻语", notes = "发布轻语")
    public ResponseWrapper publishWhisper(HttpServletRequest request, MillionWhisper millionWhisper) {
        return millionWhisperService.publishWhisper(request, millionWhisper);
    }

}

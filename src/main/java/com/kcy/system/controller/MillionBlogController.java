package com.kcy.system.controller;

import com.kcy.common.base.BaseController;
import com.kcy.common.constant.WebConst;
import com.kcy.common.model.ResponseUtils;
import com.kcy.common.model.ResponseWrapper;
import com.kcy.system.model.MillionBlog;
import com.kcy.system.service.MillionBlogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class MillionBlogController extends BaseController {

    @Autowired
    private MillionBlogService millionBlogService;

    @ApiOperation(value = "添加博客", notes = "发布博客")
    @PostMapping("/releaseBlog")
    public ResponseWrapper releaseBlog(MillionBlog millionBlog, HttpServletRequest request) throws Exception {
        return millionBlogService.releaseBlog(millionBlog, request);
    }

    @ApiOperation(value = "首页博客分页", notes = "博客分页")
    @PostMapping("/getFindAll")
    public ResponseWrapper getFindAll(@RequestParam Map<String, Object> param) {
        return millionBlogService.findAll(param);
    }
}

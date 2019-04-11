package com.kcy.system.service;

import com.kcy.common.model.ResponseWrapper;
import com.kcy.system.model.MillionBlog;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface MillionBlogService {
    ResponseWrapper releaseBlog(MillionBlog millionBlog, HttpServletRequest request);

    ResponseWrapper getBlogPageData();

    ResponseWrapper findAll(Map<String, Object> param);
}

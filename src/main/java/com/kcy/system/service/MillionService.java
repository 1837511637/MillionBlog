package com.kcy.system.service;


import com.kcy.common.model.ResponseWrapper;

import javax.servlet.http.HttpServletRequest;

public interface MillionService {
    ResponseWrapper getIndexData();

    ResponseWrapper getBlogDetails(HttpServletRequest request, Integer id);

    ResponseWrapper getArchives();

    ResponseWrapper getMenuDatas(HttpServletRequest request);
}

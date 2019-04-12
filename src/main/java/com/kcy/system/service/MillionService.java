package com.kcy.system.service;


import com.kcy.common.model.ResponseWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

public interface MillionService {
    ResponseWrapper getIndexData();

    ResponseWrapper getBlogDetails(HttpServletRequest request, Integer id);

    ResponseWrapper getArchives();
}

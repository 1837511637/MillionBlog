package com.kcy.system.service;


import com.kcy.common.model.ResponseWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface MillionService {
    ResponseWrapper getIndexData();

    ResponseWrapper getBlogDetails(Long id);
}

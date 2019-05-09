package com.kcy.system.service;

import com.kcy.common.model.ResponseWrapper;
import com.kcy.system.model.MillionWhisper;

import javax.servlet.http.HttpServletRequest;

public interface MillionWhisperService {
    ResponseWrapper publishWhisper(HttpServletRequest request, MillionWhisper millionWhisper);
}

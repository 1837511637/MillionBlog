package com.kcy.system.service;

import com.kcy.common.model.ResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface MillionUserService {
    ResponseWrapper login(String username, String password , HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}

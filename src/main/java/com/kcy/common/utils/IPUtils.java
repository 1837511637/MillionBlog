package com.kcy.common.utils;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {

    /**
     * @param request 请求
     * @return IP Address
     */
    public static String getIpAddrByRequest(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;//最后用三元表达式判断是否是本地IP；
    }

    /**
     * 判断是否是ajax请求
     * */
    public static boolean isAjax(HttpServletRequest request) {
        if(request == null) {
            return false;
        }
        String requestType = request.getHeader("X-Requested-With");
        if("XMLHttpRequest".equals(requestType)) {
            return true;
        }
        return false;
    }

}

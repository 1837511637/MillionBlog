package com.kcy.common.utils;

import com.kcy.common.constant.RedisConst;
import com.kcy.common.constant.WebConst;
import com.kcy.common.redis.RedisComponent;
import com.kcy.system.model.MillionUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BlogUtils {

    /**
     * 判断是否是跳转路径
     * */
    public static boolean isUrlPage(String url) {
        String[] var4 = WebConst.pageUrl;
        int var3 = WebConst.pageUrl.length;
        for(int var2 = 0; var2 < var3; ++var2) {
            String s = var4[var2];
            if (url.indexOf(s) > -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是跳转需要登录路径
     * */
    public static boolean isLoginInterceptPage(String url) {
        String[] var4 = WebConst.loginInterceptUrl;
        int var3 = WebConst.loginInterceptUrl.length;
        for(int var2 = 0; var2 < var3; ++var2) {
            String s = var4[var2];
            if (url.indexOf(s) > -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回当前登录用户
     *
     * @return
     */
    public static MillionUser getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (null == session) {
            return null;
        }
        return (MillionUser) session.getAttribute(RedisConst.LOGIN_SESSION_KEY);
    }

    /**
     * 获取cookie中的用户id
     *
     * @param request
     * @return
     */
    public static Integer getCookieUid(HttpServletRequest request) {
        if (null != request) {
            Cookie cookie = cookieRaw(RedisConst.USER_IN_COOKIE, request);
            if (cookie != null && cookie.getValue() != null) {
                try {
                    String uid = ConfidentialityUtils.decryptTripleDesToString(cookie.getValue(), WebConst.AES_SALT);
                    return Misc.parseInteger(uid);
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    /**
     * 从cookies中获取指定cookie
     *
     * @param name    名称
     * @param request 请求
     * @return cookie
     */
    private static Cookie cookieRaw(String name, HttpServletRequest request) {
        javax.servlet.http.Cookie[] servletCookies = request.getCookies();
        if (servletCookies == null) {
            return null;
        }
        for (javax.servlet.http.Cookie c : servletCookies) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * 判断用户2个小时以内对同一篇博客只能增加一次阅读量
     * */
    public static boolean checkHitsFrequency(HttpServletRequest request, Integer id, RedisComponent redisComponent) {
        String ip = IPUtils.getIpAddrByRequest(request);
        String blogView = RedisConst.BLOG_VIEW.replace("IP", ip).replace("BLOGID", Misc.getString(id));
        Integer count = Misc.parseInteger(redisComponent.getOpsForValue(blogView));
        if(count != null && count > 0) {
            return true;
        }
        redisComponent.opsForValue(blogView, Misc.getString(1), WebConst.HITS_LIMIT_TIME.longValue());
        return false;
    }

}

package com.kcy.common.utils;

import com.kcy.common.constant.RedisConst;
import com.kcy.common.constant.WebConst;
import com.kcy.common.redis.RedisService;
import com.kcy.system.model.MillionUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class BlogUtils {
    private static String[] symbols = {"。", ".", "!", "！", "，", ",", " "};

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
     * 判断是否是需要评论者信息的路径
     * */
    public static boolean isEvalMsgPage(String url) {
        String[] var4 = WebConst.evaluateInterceptUrl;
        int var3 = WebConst.evaluateInterceptUrl.length;
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
    public static boolean checkHitsFrequency(HttpServletRequest request, Integer id, RedisService redisService) {
        String ip = IPUtils.getIpAddrByRequest(request);
        String blogView = RedisConst.BLOG_VIEW.replace("IP", ip).replace("BLOGID", Misc.getString(id));
        Integer count = Misc.parseInteger(Misc.getString(redisService.get(blogView)));
        if(count != null && count > 0) {
            return true;
        }
        redisService.set(blogView, Misc.getString(1), WebConst.HITS_LIMIT_TIME.longValue());
        return false;
    }

    /**
     * 获取网站icon
     * */
    public static String getIcon(String url) {
        if(!Misc.isStringEmpty(url)) {
            url += WebConst.ico;
            if(getRource(url)) {
                return url;
            } else {
                return WebConst.DEFAULT_HEADIMG;
            }
        }
        return url;
    }

    /**
     * 判断网络给定地址图片是否存在
     * */
    public static boolean getRource(String source) {
        try {
            URL url = new URL(source);
            URLConnection uc = url.openConnection();
            InputStream in = uc.getInputStream();
            if (source.equalsIgnoreCase(uc.getURL().toString())) {
                in.close();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断网站地址是否失效
     * */
    public static boolean getLinkInvalid(String link) {
        boolean fale = true;
        try {
            URL url = new URL(link);
            InputStream inputStream = url.openStream();
        } catch (Exception e) {
            //System.out.println("连接打不开!");
            fale = false;
        }
        return fale;
    }

    /**
     * 获取博客的第一句话
     * */
    public static String getFirstWord(String cropcontent) {
        String content = "";
        for(String symbol : symbols) {
            if(cropcontent.contains(symbol)) {
                content = cropcontent.substring(0, cropcontent.lastIndexOf(symbol));
                return content;
            }
        }
        return content;
    }

    /**
     * 判断是否登录
     * */
    public static boolean isLogin(HttpServletRequest request) {
        MillionUser loginUser = BlogUtils.getLoginUser(request);
        boolean isLogin;
        if(loginUser != null) {
            isLogin = true;
        } else {
            isLogin = false;
        }
        return isLogin;
    }


    /**
     * 获取默认头像
     * */
    public static String getDefaultHeadimg(String url) {
        if(Misc.isStringEmpty(url)) {
            return WebConst.DEFAULT_HEADIMG;
        }
        return url;
    }

    public static Boolean isHttpOrHttps(String url) {
        if(Misc.isStringEmpty(url)) {
            return false;
        }
        Boolean fale = false;
        for(String protocol : WebConst.httpOrHttps) {
            int length = protocol.length();
            if(url.length() > length && url.substring(0, length).equals(protocol)) {
                fale = true;
                break;
            }
        }
        return fale;
    }

}

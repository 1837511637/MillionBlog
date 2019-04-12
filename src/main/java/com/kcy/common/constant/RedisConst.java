package com.kcy.common.constant;

import org.springframework.stereotype.Component;

public class RedisConst {
    //当前的登录用户
    public static String LOGIN_SESSION_KEY = "login_user";
    //当前登录用户id
    public static final String USER_IN_COOKIE = "S_L_ID";
    //登录失败次数
    public static final String LOGIN_ERROR_COUNT = "login_error_count:IP";
    //右侧菜单栏最新博客
    public static final String MENU_BLOG = "menu_blog";
    //右侧菜单栏最新留言
    public static final String MENU_EVAL = "menu_eval";
    //右侧菜单栏标签
    public static final String MENU_LABEL = "menu_label";
    //页头类型
    public static final String HEAD_TYPE = "head_type";
    //主页博客与轻语数据
    public static final String INDEX_RESPONSEWRAPPER = "index_responsewrapper";
    //阅读量限制
    public static final String BLOG_VIEW = "IP:BLOGID";
    //归档博客
    public static final String ARCHIVES_BLOG = "archives_blog";

}

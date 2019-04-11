package com.kcy.common.constant;

import com.kcy.system.model.MillionUser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebConst {

    /**
     * 需要右侧菜单栏数据的路径
     * */
    public static String[] pageUrl = new String[]{"/","/blog/","/archives","/guestbook","/about","/links","/read"};

    /**
     * 需要登录拦截路径
     * */
    public static String[] loginInterceptUrl = new String[]{"/blog/releaseBlog", "/read"};

    /**
     * aes加密加盐
     */
    public static String AES_SALT = "zhanglan";

    /**
     * 文章最多可以输入的文字数
     */
    public static final int MAX_TEXT_COUNT = 200000;

    /**
     * 评论最多可以输入的文字数
     */
    public static final int MAX_TEXT_EVALUATE = 200;

    /**
     * 文章标题最多可以输入的文字个数
     */
    public static final int MAX_TITLE_COUNT = 200;

    /**
     * 点击次数超过多少更新到数据库
     */
    public static final int HIT_EXCEED = 10;

    /**
     * 上传文件最大1M
     */
    public static Integer MAX_FILE_SIZE = 1048576;

    /**
     * 成功返回
     */
    public static String SUCCESS_RESULT = "SUCCESS";

    /**
     * 同一篇文章在2个小时内无论点击多少次只算一次阅读
     */
    public static Integer HITS_LIMIT_TIME = 7200;

    /**
     * 允许的视频转码格式(mencoder)
     */
    public static String[] allowIMG = { ".png", ".jpg", ".gif" };

    /**
     * 图片上传路径前缀
     * */
    public static String uploadImgPrefix = "E:";




}

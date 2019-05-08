package com.kcy.system.service;


import com.kcy.common.model.ResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface MillionService {
    //跳转首页
    ResponseWrapper getIndexData();
    //跳转博客页
    ResponseWrapper getBlogDetails(HttpServletRequest request, Integer id);
    //跳转档案页
    ResponseWrapper getArchives();
    //留言
    ResponseWrapper getGuestbooks(Integer page);
    //右侧菜单
    ResponseWrapper getMenuDatas(HttpServletRequest request);
    //评论者数据
    ResponseWrapper getEvalMsg(HttpServletRequest request);
    //条件查询
    ResponseWrapper query(Map<String, Object> map);
    //轻语
    ResponseWrapper getWhisperDatas(Integer page);
}

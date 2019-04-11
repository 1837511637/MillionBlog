package com.kcy.common.filter;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.kcy.common.config.GlobalExceptionHandler;
import com.kcy.common.constant.RedisConst;
import com.kcy.common.constant.WebConst;
import com.kcy.common.model.ResponseUtils;
import com.kcy.common.model.ResponseWrapper;
import com.kcy.common.redis.RedisComponent;
import com.kcy.common.utils.BlogUtils;
import com.kcy.common.utils.IPUtils;
import com.kcy.system.dao.MillionBlogMapper;
import com.kcy.system.dao.MillionEvaluationMapper;
import com.kcy.system.dao.MillionLabelMapper;
import com.kcy.system.dao.MillionUserMapper;
import com.kcy.system.model.MillionBlog;
import com.kcy.system.model.MillionEvaluation;
import com.kcy.system.model.MillionLabel;
import com.kcy.system.model.MillionUser;
import com.kcy.system.vo.VoMenuBlog;
import com.kcy.system.vo.VoMenuEvaluate;
import com.kcy.system.vo.VoMenuLabel;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该拦截器用于返回页面右侧栏数据
 * */
@Log4j
@Component
public class MenuDatasInterceptor extends HandlerInterceptorAdapter {

    private static String ERROR_VIEWNAME  = "/error";

    @Autowired
    private RedisComponent redisComponent;
    @Autowired
    private MillionBlogMapper millionBlogMapper;
    @Autowired
    private MillionEvaluationMapper millionEvaluationMapper;
    @Autowired
    private MillionLabelMapper millionLabelMapper;
    @Autowired
    private MillionUserMapper millionUserMapper;

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     *    执行下一个拦截器,直到所有的拦截器都执行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求路径
        String requestURI = request.getRequestURI();
        //判断路径是否需要拦截
        if(BlogUtils.isLoginInterceptPage(requestURI)) {
            //获取session中是否存在用户对象
            MillionUser loginUser = BlogUtils.getLoginUser(request);
            if(loginUser == null) {
                //获取cookie中是否存在用户id
                Integer uid = BlogUtils.getCookieUid(request);
                if(uid == null || uid == 0) {
                    if (IPUtils.isAjax(request)) {
                        response.getWriter().write("isAjax");
                    } else {
                        response.sendRedirect(ERROR_VIEWNAME);
                    }
                    return false;
                }
                //查询用户信息
                MillionUser millionUser = millionUserMapper.selectByPrimaryKey(uid);
                if(millionUser == null || !"1".equals(millionUser.getStatus())) {
                    if (IPUtils.isAjax(request)) {
                        response.getWriter().write("isAjax");
                    } else {
                        response.sendRedirect(ERROR_VIEWNAME);
                    }
                    return false;
                }
                //存入session
                request.getSession().setAttribute(RedisConst.LOGIN_SESSION_KEY, millionUser);
            }
        }
        log.info("请求路径:" + requestURI);
        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String requestURI = request.getRequestURI();
        if(BlogUtils.isUrlPage(requestURI) && modelAndView != null) {
            responseMenuData(modelAndView);
        }
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public void responseMenuData(ModelAndView modelAndView) {
        ResponseWrapper responseWrapper = ResponseUtils.successResponse("");
        Map<String, Object> param = new HashMap();
        param.put("limit", 5);
        //获取热门博客
        List<VoMenuBlog> voMenuBlogs = (List<VoMenuBlog>)redisComponent.getOpsForObject(RedisConst.MENU_BLOG);
        if(voMenuBlogs == null) {
            voMenuBlogs = new ArrayList();
            List <MillionBlog> millionBlogs = millionBlogMapper.theLatestData(param);
            for(MillionBlog millionBlog : millionBlogs) {
                VoMenuBlog voMenuBlog = new VoMenuBlog();
                voMenuBlog.setId(millionBlog.getId());
                voMenuBlog.setTitle(millionBlog.getTitle());
                voMenuBlogs.add(voMenuBlog);
            }
            redisComponent.opsForValue(RedisConst.MENU_BLOG, voMenuBlogs);
        }
        responseWrapper.addAttribute("menuBlogs", voMenuBlogs);

        //最新留言
        List<VoMenuEvaluate> voMenuEvaluates = (List<VoMenuEvaluate>)redisComponent.getOpsForObject(RedisConst.MENU_EVAL);
        if(voMenuEvaluates == null) {
            voMenuEvaluates = new ArrayList();
            param.put("type", "3");
            List <MillionEvaluation> millionEvaluations = millionEvaluationMapper.theLatestData(param);
            for(MillionEvaluation millionEvaluation : millionEvaluations) {
                VoMenuEvaluate voMenuEvaluate = new VoMenuEvaluate();
                voMenuEvaluate.setName(millionEvaluation.getName());
                voMenuEvaluate.setContent(millionEvaluation.getContent());
                voMenuEvaluate.setWeblink(millionEvaluation.getWeblink());
                voMenuEvaluates.add(voMenuEvaluate);
            }
            redisComponent.opsForValue(RedisConst.MENU_EVAL, voMenuEvaluates);
        }
        responseWrapper.addAttribute("menuEvals", voMenuEvaluates);
        //标签
        List<VoMenuLabel> voMenuLabels = (List<VoMenuLabel>)redisComponent.getOpsForObject(RedisConst.MENU_LABEL);
        if(voMenuLabels == null) {
            voMenuLabels = new ArrayList();
            List<MillionLabel> millionLabels = millionLabelMapper.findAll(null);
            for(MillionLabel millionLabel : millionLabels) {
                VoMenuLabel voMenuLabel = new VoMenuLabel();
                voMenuLabel.setId(millionLabel.getId());
                voMenuLabel.setName(millionLabel.getName());
                voMenuLabels.add(voMenuLabel);
            }
            redisComponent.opsForValue(RedisConst.MENU_LABEL, voMenuLabels);
        }
        responseWrapper.addAttribute("menuLabels", voMenuLabels);
        modelAndView.addObject("menuresult", responseWrapper);
    }
}

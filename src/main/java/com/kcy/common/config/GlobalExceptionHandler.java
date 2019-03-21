package com.kcy.common.config;

import com.kcy.common.model.ResponseUtils;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;

/**  
 * @Title: GlobalExceptionHandler.java
 * @Package com.kcy.handler
 * @Description: TODO
 * @author Kcy
 * @date 2019-3-10下午3:09:59
 */
@Log4j
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final String DEFAULT_ERROR_VIEW  = "error";

	/*@ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        String header = req.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(header) ? true : false;
        if(isAjax) {
            log.info("ajax请求");
            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
            mav.addObject(ResponseUtils.errorResponse("网络繁忙"));
            return mav;
        } else {
            log.info("页面请求");
            ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
            mav.addObject("timestamp","2019-03-11 19:12:12");
            mav.addObject("exception", e);
            mav.addObject("url", req.getRequestURL());
            mav.addObject("error", "error");
            mav.addObject("message", "error");
            return mav;
        }
    }*/

    /**
     * 系统异常处理，比如：404,500
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e){
        log.error("", e);
        ModelAndView modelAndView = new ModelAndView();
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }
}

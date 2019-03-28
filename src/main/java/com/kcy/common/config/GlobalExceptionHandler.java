package com.kcy.common.config;

import com.kcy.common.model.ResponseUtils;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.rmi.runtime.Log;

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

	private static final String ERROR_VIEWNAME  = "error";

	private static final String ERROR_MSG = "网络繁忙";

    /**
     * 系统异常处理，比如：404,500
     * @param request
     * @param e
     * @return
     * @throws Exception*/

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e){
        //如果是404
        /*if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            modelAndView.setViewName(ERROR_VIEWNAME);
            return modelAndView;
        }*/
        log.error(e.getMessage(),e);
        String requestType = request.getHeader("X-Requested-With");
        if("XMLHttpRequest".equals(requestType)){
            //System.out.println("AJAX请求..");
            ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());
            modelAndView.addObject(ResponseUtils.errorResponse(ERROR_MSG));
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName(ERROR_VIEWNAME);
            return modelAndView;
        }
    }
}

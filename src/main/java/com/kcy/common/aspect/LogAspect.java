package com.kcy.common.aspect;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/*@Aspect
@Component*/
@Log4j
public class LogAspect {

    //切面注解：目录为com.kcy.system.controller的所有方法
    @Pointcut("execution(public * com.kcy.system.controller.*.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("——————————————————————————————————");
        log.info("URL : " + request.getRequestURL().toString());
        //HTTP方法
        log.info("HTTP_METHOD : " + request.getMethod());
        //ip
        log.info("IP : " + request.getRemoteAddr());
        //类方法
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature
                ().getName());
        //参数
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        log.info("——————————————————————————————————");
    }

    @AfterReturning("pointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        // 处理完请求，返回内容
//        logger.info("WebLogAspect.doAfterReturning()");
    }

}

package com.kcy.common.config;

import com.kcy.common.filter.MenuDatasInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

//spring框架配置
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private MenuDatasInterceptor menuDatasInterceptor;

    //解决跨域问题
    //前端报错 Access-Control-Allow-Origin 的前端问题解决
    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","DELETE","PUT")
                .maxAge(3600);
    }*/

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        //在Spring添加拦截器之前先自己创建一下这个Spring Bean，这样就能在Spring映射这个拦截器前，把拦截器中的依赖注入给完成了。
        registry.addInterceptor(menuDatasInterceptor);
        super.addInterceptors(registry);
    }

}

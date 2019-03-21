package com.kcy.common.config;

import com.kcy.common.filter.MenuDatasInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//spring框架配置
@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

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

    @Bean
    public MenuDatasInterceptor getMenuDatasInterceptor() {
        return new MenuDatasInterceptor();
    }

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        //配置自定义拦截器
        registry.addInterceptor(getMenuDatasInterceptor());
    }

}

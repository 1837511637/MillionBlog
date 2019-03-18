
package com.kcy.common.config;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.io.IOException;

@Configuration
public class BeetlConfig {

    @Bean(initMethod="init")
    public BeetlGroupUtilConfiguration beetlConfiguration(){

        BeetlGroupUtilConfiguration configuration=new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        String path=null;
        try {
            path = patternResolver.getResource("classpath:/").getFile().getPath();
            WebAppResourceLoader resourceLoader=new WebAppResourceLoader(path);
            configuration.setResourceLoader(resourceLoader);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return configuration;
    }

    @Bean
    public BeetlSpringViewResolver getBeetlSpringViewResolver(){
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setPrefix("/templates/views/");   //前缀
        beetlSpringViewResolver.setSuffix(".html");     //后缀
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlConfiguration());
        return beetlSpringViewResolver;
    }
}

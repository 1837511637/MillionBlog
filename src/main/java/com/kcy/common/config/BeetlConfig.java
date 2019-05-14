
package com.kcy.common.config;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Configuration
public class BeetlConfig {

    @Bean(initMethod="init")
    public BeetlGroupUtilConfiguration beetlConfiguration(){
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        //ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader == null){
            classLoader = SpringBootApplication.class.getClassLoader();
        }
        try {
            beetlGroupUtilConfiguration.setConfigProperties(PropertiesLoaderUtils.loadAllProperties("beetl.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClasspathResourceLoader cploder = new ClasspathResourceLoader(classLoader, "");
        beetlGroupUtilConfiguration.setResourceLoader(cploder);
        beetlGroupUtilConfiguration.init();
        //如果使用了优化编译器，涉及到字节码操作，需要添加ClassLoader
        GroupTemplate groupTemplate = beetlGroupUtilConfiguration.getGroupTemplate();
        groupTemplate.setClassLoader(classLoader);
        return beetlGroupUtilConfiguration;
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

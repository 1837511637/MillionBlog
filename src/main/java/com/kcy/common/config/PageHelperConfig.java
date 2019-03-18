package com.kcy.common.config;


import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@EnableCaching
@ServletComponentScan
public class PageHelperConfig {

    @Value(value = "${pageHelper.offsetAsPageNum}")
    private String offsetAsPageNum;

    @Value(value = "${pageHelper.rowBoundsWithCount}")
    private String rowBoundsWithCount;

    @Value(value = "${pageHelper.reasonable}")
    private String reasonable;

    @Value(value = "${pageHelper.dialect}")
    private String dialect;

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();
        properties.setProperty("offsetAsPageNum",offsetAsPageNum);
        //行数界限，true：输入页数大于数据总页数将等于最后一页
        properties.setProperty("rowBoundsWithCount",offsetAsPageNum);
        properties.setProperty("reasonable",offsetAsPageNum);
        //配置mysql数据库的方言
        properties.setProperty("dialect",dialect);
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}

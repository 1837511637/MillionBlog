package com.kcy.common.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zh
 * @ClassName cn.kcy.common.config.Swagger2Config
 * @Description
 * @date 2019-3-10 22:12:31
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {
    //访问路径: http://localhost:8080/swagger-ui.html
    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置 规格 存储方式
    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled).select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.any())
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build().pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("KCY博客接口文档")
                .description("million | KCY的博客")
                // 作者信息
                .contact(new Contact("million", "http://kcyBlog.top/", "iwantn1@163.com"))
                .version("1.0.0")
                .build();
    }

}

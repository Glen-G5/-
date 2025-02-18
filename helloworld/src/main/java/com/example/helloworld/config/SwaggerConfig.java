package com.example.helloworld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration  //告诉Spring容器，这个类是一个配置类
@EnableSwagger2 //启动Swagger2功能
public class SwaggerConfig {
    /*
    配置swagger2相关的bean
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //包下面的api都交给swagger2管理
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any()).build();
    }
    //api文档页面显示信息
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("演示文档API")//标题
                .description("学习Swagger2项目")//描述
                .version("1.0")//版本
                .build();
    }

}

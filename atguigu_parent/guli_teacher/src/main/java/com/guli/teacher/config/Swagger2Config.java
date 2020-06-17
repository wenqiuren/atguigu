package com.guli.teacher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket docket(){
        return  new Docket(DocumentationType.SWAGGER_2).groupName("webApi").apiInfo(apiInfo()).select().build();
    }
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("网站-讲师api文档")
                .description("本文档讲述了讲师服务接口定义")
                .version("1.0")
                .contact(new Contact("atguigu","https://www.baidu.com","784332919@qq.com"))
                .build();
    }
}
package com.tony.springboot.demo.config;

import com.tony.springboot.demo.constant.Constants;
import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by czx on 2021/04/02.
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage(Constants.BASE_PACKAGE + ".controller"))//按包扫描,也可以扫描共同的父包，不会显示basic-error-controller
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(getGlobalRequestParameters());
    }

    private ApiInfo apiInfo() {
        return  new ApiInfoBuilder()
                .title("项目接口文档")
                .description("接口文档")
                .version("1.0")
                .build();
    }

    //生成全局通用参数
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name(Constants.TOKEN)
                .description("token")
                .in(ParameterType.HEADER)
                .required(false)
                .build());
        return parameters;
    }
}


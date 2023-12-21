package com.rise;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author 张牧之
 * @date 2022-12-04 19:01:23
 * @Email zhanglichang99@gmail.com
 */


@SpringBootApplication
@ComponentScan(value="com.rise",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class)},
        includeFilters = {@ComponentScan.Filter(type=FilterType.ANNOTATION,classes={Controller.class})})
public class AuthorizeApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizeApplication.class);
        System.out.println("=================================AuthorizeApplication start success.=================================");
    }
}

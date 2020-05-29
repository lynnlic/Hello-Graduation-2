package com.cms.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //registry.addResourceHandler("/data/**").addResourceLocations("file:D:/cms/");
        registry.addResourceHandler("/icon/**").addResourceLocations("file:/D:/cms/data/icon/");
        registry.addResourceHandler("/data/**").addResourceLocations("file:/D:/cms/data/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}

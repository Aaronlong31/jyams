package com.jyams.util;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author zhanglong
 * 
 *         Nov 11, 2012 7:39:10 PM
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**", "/images/**", "/css/**")
                .addResourceLocations("/js/", "/images/", "/css/");
    }

    @Override
    protected void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        QueryArgumentResolver queryArgumentResolver = new QueryArgumentResolver();
        argumentResolvers.add(queryArgumentResolver);
    }
}

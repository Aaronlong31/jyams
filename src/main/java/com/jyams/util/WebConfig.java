package com.jyams.util;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.jyams.exceptionhandle.JyamsDefaultExceptionResolver;
import com.jyams.exceptionhandle.RequestParameterExceptionResolver;

/**
 * @author zhanglong
 * 
 *         Nov 11, 2012 7:39:10 PM
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**", "/images/**", "/css/**", "/html/**")
                .addResourceLocations("/js/", "/images/", "/css/", "/html/");
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        QueryArgumentResolver queryArgumentResolver = new QueryArgumentResolver();
        argumentResolvers.add(queryArgumentResolver);
    }

    @Override
    protected void configureHandlerExceptionResolvers(
            List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new JyamsDefaultExceptionResolver());
        exceptionResolvers.add(new RequestParameterExceptionResolver());
    }
}

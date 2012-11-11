package com.jyams.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.jyams.project.query.ProjectQuery;
import com.jyams.util.search.Query;
import com.jyams.util.search.SearchFilter;

/**
 * @author zhanglong
 * 
 *         Nov 11, 2012 10:12:04 PM
 */
public class QueryArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> parameterType = parameter.getParameterType();
        return Query.class.isAssignableFrom(parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        Class<?> parameterType = parameter.getParameterType();
        HttpServletRequest request = webRequest
                .getNativeRequest(HttpServletRequest.class);
        SearchFilter searchFilter = SearchFilter.build(request);
        if (ProjectQuery.class.isAssignableFrom(parameterType)) {
            return new ProjectQuery(searchFilter);
        }
        return null;
    }

}

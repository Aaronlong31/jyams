package com.jyams.exceptionhandle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhanglong
 * 
 *         2012-10-24
 */
public class RequestParameterExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        if (ex instanceof MissingServletRequestParameterException) {
            return handleMissingServletRequestParameterException((MissingServletRequestParameterException) ex, response);
        }
        if (ex instanceof ServletRequestBindingException) {
            return handleServletRequestBindingException(ex, response);
        }
        return null;
    }

    private ModelAndView handleServletRequestBindingException(Exception ex, HttpServletResponse response) {
        return null;
    }

    private ModelAndView handleMissingServletRequestParameterException(MissingServletRequestParameterException ex,
            HttpServletResponse response) {
        return null;
    }

}

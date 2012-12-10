package com.jyams.exceptionhandle;

import com.jyams.exception.NoPermissionException;
import com.jyams.exception.UnLoginException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: zhanglong
 * Date: 12-12-10
 * Time: 下午11:22
 */
public class AuthorizationExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {

        if (ex instanceof UnLoginException) {
            handlerUnLoginException((UnLoginException) ex, request, response);
        }

        if (ex instanceof NoPermissionException) {
            handlerNoPermissionException((NoPermissionException) ex, request, response);
        }

        return null;
    }

    private ModelAndView handlerNoPermissionException(NoPermissionException ex, HttpServletRequest request,
                                                      HttpServletResponse response) {
        return null; //TODO
    }

    private ModelAndView handlerUnLoginException(UnLoginException ex, HttpServletRequest request,
                                                 HttpServletResponse response) {
        return null; //TODO
    }
}

package com.jyams.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jyams.exception.InActiveUserException;
import com.jyams.exception.NoPermissionException;
import com.jyams.exception.UnLoginException;

/**
 * User: zhanglong Date: 12-12-10 Time: 下午9:22
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        NoAuth noAuth = handlerMethod.getMethodAnnotation(NoAuth.class);

        if (noAuth != null) {
            return true;
        }

        if (!SecurityUtils.isLogin()) {
            throw new UnLoginException();
        }

        if (!SecurityUtils.isActive()) {
            throw new InActiveUserException();
        }

        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        if (auth == null) {
            return true;
        }

        if (!SecurityUtils.hasPermission(auth.value())) {
            throw new NoPermissionException();
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
    }

}

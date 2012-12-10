package com.jyams.security;

import com.jyams.exception.NoPermissionException;
import com.jyams.exception.UnLoginException;
import com.jyams.secure.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: zhanglong
 * Date: 12-12-10
 * Time: 下午9:22
 */
public class AuthorizationInterceptor implements HandlerInterceptor {

    private PermissionChecker permissionChecker;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        String permission = auth.value();

        User user = SecurityUtils.getCurrentUser();

        if (user == null) {
            throw new UnLoginException();
        }


        if (this.permissionChecker.checkPermission(user.getUsername(), permission)) {
            throw new NoPermissionException();
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
    }

    @Autowired
    public void setPermissionChecker(PermissionChecker permissionChecker) {
        this.permissionChecker = permissionChecker;
    }

}
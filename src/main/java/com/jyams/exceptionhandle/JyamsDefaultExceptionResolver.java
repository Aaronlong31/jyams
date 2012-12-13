package com.jyams.exceptionhandle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.jyams.exception.BusinessException;
import com.jyams.exception.InActiveUserException;
import com.jyams.exception.NoPermissionException;
import com.jyams.exception.UnLoginException;

/**
 * @author zhanglong
 *         <p/>
 *         Nov 25, 2012 1:29:56 AM
 */
public class JyamsDefaultExceptionResolver implements HandlerExceptionResolver {

    private final Logger logger = LoggerFactory.getLogger(JyamsDefaultExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) {

        if (handler == null || !(handler instanceof HandlerMethod)) {
            return null;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        String errorPage = null;
        String errorCode = null;

        logger.error(ex.getMessage(), ex);

        if (ex instanceof BusinessException) {
            logger.error(ex.getMessage(), ex);
            ErrorPage errorPageA = handlerMethod.getMethodAnnotation(ErrorPage.class);
            if (errorPageA != null) {
                errorPage = errorPageA.value();
            } else {
                errorPage = "error";
            }
        } else if (ex instanceof UnLoginException) {
            errorPage = "redirect:toLogin";
            errorCode = "NEED_LOGIN";
        } else if (ex instanceof NoPermissionException) {
            errorPage = "noPermission";
            errorCode = "NO_PERMISSION";
        } else if (ex instanceof InActiveUserException) {
            errorPage = "redirect:toLogin";
            errorCode = "USER_INACTIVE";
        } else {
            logger.error(ex.getMessage(), ex);
            errorPage = "error";
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", ex.getMessage());
        mav.addObject("exception", ex);
        mav.addObject("errorCode", errorCode);

        ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
        if (responseBody != null) {
            mav.setView(new MappingJackson2JsonView());
        } else {
            mav.setViewName(errorPage);
        }

        return mav;
    }

}

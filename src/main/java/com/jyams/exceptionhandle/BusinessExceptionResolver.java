package com.jyams.exceptionhandle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.jyams.exception.BusinessException;
import com.jyams.exception.ErrorInfo;

/**
 * @author zhanglong
 * 
 *         Nov 25, 2012 1:29:56 AM
 */
public class BusinessExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex) {

        if (ex instanceof BusinessException) {
            return handleBusinessException((HandlerMethod) handler, response,
                    (BusinessException) ex);
        }

        return null;
    }

    private ModelAndView handleBusinessException(HandlerMethod handlerMethod,
            HttpServletResponse response, BusinessException ex) {
        ResponseBody responseBody = handlerMethod
                .getMethodAnnotation(ResponseBody.class);
        ErrorInfo errorInfo = buildErrorInfo(ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject(errorInfo);
        response.setStatus(errorInfo.getStatus());
        if (responseBody != null) {
            mav.setView(new MappingJackson2JsonView());
        } else {
            mav.setViewName("error");
        }
        return mav;
    }

    private ErrorInfo buildErrorInfo(BusinessException ex) {
        ErrorInfo ei = new ErrorInfo(400, 400, ex.getMessage());
        return ei;
    }
}

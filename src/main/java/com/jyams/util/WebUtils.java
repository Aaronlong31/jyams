package com.jyams.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author zhanglong
 * 
 *         Nov 10, 2012 10:02:38 PM
 */
public final class WebUtils extends org.springframework.web.util.WebUtils {

    private WebUtils() {
    }

    public static HttpServletRequest getHttpRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
    }

    public static HttpSession getHttpSession() {
        return getHttpRequest().getSession(true);
    }

    public static String getIp() {
        return getHttpRequest().getRemoteAddr();
    }

    public static Object getSessionAttribute(String name) {
        return getHttpSession().getAttribute(name);
    }

    public static String getCookie(String name) {
        Cookie cookie = getCookie(getHttpRequest(), name);
        if (cookie == null) {
            return null;
        }
        return cookie.getValue();
    }

}

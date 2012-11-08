package com.jyams.util;

import javax.servlet.ServletRequest;

import org.apache.commons.lang.StringUtils;

public class RequestUtil {

    public static Integer getInteger(ServletRequest request, String name) {
        String parameter = request.getParameter(name);
        if (StringUtils.isNotBlank(parameter)) {
            return Integer.parseInt(parameter);
        }
        return null;
    }

    public static Long getLong(ServletRequest request, String name) {
        String parameter = request.getParameter(name);
        if (StringUtils.isNotBlank(parameter)) {
            return Long.parseLong(parameter);
        }
        return null;
    }

    public static Float getFloat(ServletRequest request, String name) {
        String parameter = request.getParameter(name);
        if (StringUtils.isNotBlank(parameter)) {
            return Float.parseFloat(parameter);
        }
        return null;
    }

    public static Short getShort(ServletRequest request, String name) {
        String parameter = request.getParameter(name);
        if (StringUtils.isNotBlank(parameter)) {
            return Short.parseShort(parameter);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.isBlank(" "));
        System.out.println(StringUtils.isEmpty(" "));
        System.out.println(StringUtils.capitalize(null));

    }
}

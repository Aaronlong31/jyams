package com.jyams.exceptionhandle;

import java.lang.annotation.*;

/**
 * User: zhanglong
 * Date: 12-12-11
 * Time: 下午9:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ErrorPage {

    String value();

}

package com.jyams.security;

import java.lang.annotation.*;

/**
 * User: zhanglong
 * Date: 12-12-10
 * Time: 下午9:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {

    String value();

}

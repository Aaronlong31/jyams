package com.jyams.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * User: zhanglong
 * Date: 12-12-10
 * Time: 下午9:34
 */
@Target(ElementType.METHOD)
public @interface Auth {

    String value();

}

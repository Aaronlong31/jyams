package com.jyams.security;

import java.lang.annotation.*;

/**
 * User: zhanglong
 * Date: 12-12-13
 * Time: 下午3:35
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoAuth {
}

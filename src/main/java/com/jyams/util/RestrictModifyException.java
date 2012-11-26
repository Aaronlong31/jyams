package com.jyams.util;

import com.jyams.exception.BusinessException;

/**
 * 尝试修改不能修改的对象时，会抛出此异常
 * 
 * @author zhanglong
 * 
 */
public class RestrictModifyException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public RestrictModifyException() {
        super();
    }

    public RestrictModifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestrictModifyException(String message) {
        super(message);
    }

    public RestrictModifyException(Throwable cause) {
        super(cause);
    }

}

package com.jyams.exception;

/**
 * @author zhanglong
 * 
 *         Nov 8, 2012 9:37:26 PM
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}

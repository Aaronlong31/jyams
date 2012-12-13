package com.jyams.exception;

/**
 * User: zhanglong Date: 12-12-10 Time: 下午11:19
 */
public class UnLoginException extends AuthorizationException {

    public UnLoginException() {
        super("请先登录！");
    }

    public UnLoginException(String message) {
        super(message);
    }

    public UnLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnLoginException(Throwable cause) {
        super(cause);
    }
}

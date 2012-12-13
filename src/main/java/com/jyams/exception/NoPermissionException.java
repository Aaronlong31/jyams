package com.jyams.exception;

/**
 * User: zhanglong
 * Date: 12-12-10
 * Time: 下午11:21
 */
public class NoPermissionException extends AuthorizationException {

    public NoPermissionException() {
        super("您没有访问权限！");
    }

    public NoPermissionException(String message) {
        super(message);
    }

    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPermissionException(Throwable cause) {
        super(cause);
    }
}

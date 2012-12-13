package com.jyams.exception;

/**
 * User: zhanglong
 * Date: 12-12-11
 * Time: 下午9:08
 */
public class InActiveUserException extends AuthorizationException{

    public InActiveUserException() {
        super("用户已被禁用！");
    }
}

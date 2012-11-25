package com.jyams.exception;

/**
 * @author zhanglong
 * 
 *         Nov 25, 2012 1:36:40 AM
 */
public class ErrorInfo {

    private int code;

    private int status;

    private String message;

    public ErrorInfo(int code, int status, String message) {
        super();
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

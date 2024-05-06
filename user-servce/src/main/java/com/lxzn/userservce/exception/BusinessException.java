package com.lxzn.userservce.exception;

import com.lxzn.userservce.common.ErrorCode;

/**
 * 自定义异常类
 */
public class BusinessException extends RuntimeException {
    private int code;
    private String msg;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMassage());
        code = errorCode.getCode();
        msg = errorCode.getMassage();
    }

    public BusinessException(ErrorCode errorCode, String massage) {
        super(errorCode.getMassage());
        code = errorCode.getCode();
        msg = massage;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}

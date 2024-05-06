package com.lxzn.userservce.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @param <T>
 * @author lixin
 */
@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 3198856983456202796L;
    private int code;
    private T data;
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(int code, String message) {
        this(code, null, message);
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMassage());
    }
}

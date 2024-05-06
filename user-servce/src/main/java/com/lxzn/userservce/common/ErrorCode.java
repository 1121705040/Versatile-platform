package com.lxzn.userservce.common;

/**
 * 错误码
 *
 * @author lixin
 */
public enum ErrorCode {
    SUCCESS(0, "ok", ""),
    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求参数为空", ""),
    NOT_LOGIN(40100, "未登录", ""),
    INTERNAL_ANOMALY(50000, "系统内部异常", ""),
    NO_AUTH(40101, "没权限", "");

    private int code;
    private String massage;
    private String description;

    ErrorCode(int code, String massage, String description) {
        this.code = code;
        this.massage = massage;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMassage() {
        return massage;
    }

    public String getDescription() {
        return description;
    }
}

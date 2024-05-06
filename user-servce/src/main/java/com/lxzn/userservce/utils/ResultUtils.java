package com.lxzn.userservce.utils;

import com.lxzn.userservce.common.BaseResponse;
import com.lxzn.userservce.common.ErrorCode;

/**
 * 返回工具类
 *
 * @author lixin
 */
public class ResultUtils {
    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> fail(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }
    /**
     * 失败
     *
     * @param
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> fail(int code, String massage) {
        return new BaseResponse<>(code,massage);
    }
}

package com.lxzn.userservce.exception;

import com.lxzn.userservce.common.BaseResponse;
import com.lxzn.userservce.common.ErrorCode;
import com.lxzn.userservce.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e){
        log.error("BusinessException",e);
        return ResultUtils.fail(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse businessExceptionHandler(RuntimeException e){
        log.error("RuntimeException",e);
        return ResultUtils.fail(ErrorCode.INTERNAL_ANOMALY);
    }
}

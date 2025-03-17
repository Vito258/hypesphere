package com.elon.hypesphere.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 集中处理所有异常
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.elon.hypesphere.product.controller")
public class ExceptionControllerAdvice {
    /**
     * 处理所有验证的异常
     * @param e
     * @return
     */

    @ExceptionHandler(value = Exception.class)
    public R handleValidException(Exception e) {
        log.error("数据校验出现问题:{}, 异常类型:{}", e.getMessage(),e.getClass());
        e.printStackTrace();
        return R.error();
    }
}

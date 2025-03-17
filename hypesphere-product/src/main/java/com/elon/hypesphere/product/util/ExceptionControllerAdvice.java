package com.elon.hypesphere.product.util;

import com.elon.hypesphere.common.exception.BizCodeEnume;
import com.elon.hypesphere.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 集中处理所有异常
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.elon.hypesphere.product.controller")
public class ExceptionControllerAdvice {

    /**
     * 处理所有验证的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题:{}, 异常类型:{}", e.getMessage(), e.getClass());

        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            Map<String, String> map = new HashMap<>();
            //1、获取校验的错误结果
            bindingResult.getFieldErrors().forEach((item) -> {
                // 2、获取到错误提示
                String message = item.getDefaultMessage();
                // 3、获取到错误属性的名称
                String field = item.getField();
                map.put(field, message);
            });
            return R.error(BizCodeEnume.VALID_EXCEPTION.getCode(), BizCodeEnume.VALID_EXCEPTION.getMsg()).put("data", map);
        }
        return R.error();
    }

    /**
     * 处理所有异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable e) {
        log.error("错误:", e);
        return R.error(BizCodeEnume.UNKNOW_EXCEPTION.getCode(), BizCodeEnume.UNKNOW_EXCEPTION.getMsg());
    }
}

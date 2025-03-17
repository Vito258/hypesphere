package com.elon.hypesphere.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义注解ListValue校验器
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue,Byte> {

    // 存放校验的数据
    private Set<Byte> allowedValues = new HashSet<>();

    /**
     * 初始化方法
     * @param constraintAnnotation
     */
    @Override
    public void initialize(ListValue constraintAnnotation) {
        // 将注解中定义的 int[] values 转换为 Byte 集合
        for (int value : constraintAnnotation.vals()) {
            allowedValues.add((byte) value); // int 转 byte
        }
    }

    /**
     * 判断是否校验成功
     * @param value 需要校验的数据
     * @param constraintValidatorContext  校验器上下文
     * @return
     */
    @Override
    public boolean isValid(Byte value, ConstraintValidatorContext constraintValidatorContext) {
        // 允许值为 null 时跳过校验（如果需要校验非空，需配合 @NotNull）
        if (value == null) return true;
        return allowedValues.contains(value);
    }
}

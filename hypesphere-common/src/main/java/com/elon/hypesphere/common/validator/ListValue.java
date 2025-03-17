package com.elon.hypesphere.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义校验ListValue注解
 * @author elon
 * vals={},注解的字段只可以属于vals 的值，如果不符合便会抛出异常
 */
//
@Documented
// Constraint: 指定校验器,可以指定多个不同的校验器
@Constraint(
        validatedBy = {ListValueConstraintValidator.class}
)
// Target: 可以放在什么属性上
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListValue {
    String message() default "{com.elon.common.valid.ListValue.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    int[] vals() default {};
}

package com.aop.anno;
/*
 * @Author: CaseyL
 * @Description: com.anno
 * @Date: 2023/1/21 17:33
 * */

import com.aop.convert.Convert;

import java.lang.annotation.*;

@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateRecord {
    String desc() default "";

    Class<? extends Convert> convert();
}

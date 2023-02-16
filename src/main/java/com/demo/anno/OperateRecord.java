package com.demo.anno;
/*
 * @Author: CaseyL
 * @Description: com.anno
 * @Date: 2023/1/21 17:33
 * */


import javax.persistence.Convert;
import java.lang.annotation.*;

@Target({ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateRecord {
    String desc() default ""; // 操作类型 操作人 操作时间 余额

}

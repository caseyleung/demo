package com.aop.aspect;
/*
 * @Author: CaseyL
 * @Description: com.aop.aspect
 * @Date: 2023/1/21 17:34
 * */

import com.aop.anno.OperateRecord;
import com.aop.convert.Convert;
import com.aop.log.OperateLogDO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//@Component
@Aspect
@Component
public class OperateAspect {

        /**
         * 1.定义切入点
         * 2.横切逻辑
         * 3.织入 (spring自动处理)
         */


//    @Pointcut("@annotation(com.aop.anno.OperateRecord)")
//    public void pointcut() {
//    }

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            1, 1, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100)
    );

//    @Around("pointcut()")
    @Around(value = "@annotation(com.aop.anno.OperateRecord)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object proceed = proceedingJoinPoint.proceed();
        threadPoolExecutor.execute(() -> {
            try {
                MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
                OperateRecord annotation = methodSignature.getMethod().getAnnotation(OperateRecord.class);
                Class<? extends Convert> convert = annotation.convert();
                Convert logConvert = convert.newInstance();
                OperateLogDO operateLogDO = logConvert.convert(proceedingJoinPoint.getArgs()[0]);
//                OperateLogDO operateLogDO = new OperateLogDO();
//                operateLogDO.setOrderId();
                operateLogDO.setDesc(annotation.desc());
                operateLogDO.setResult(proceed.toString());

                System.out.println("insert operate log :" + operateLogDO);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return proceed;
    }
}

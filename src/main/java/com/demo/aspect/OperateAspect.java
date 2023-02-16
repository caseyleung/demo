package com.demo.aspect;
/*
 * @Author: CaseyL
 * @Description: com.aop.aspect
 * @Date: 2023/1/21 17:34
 * */


import com.demo.anno.OperateRecord;
import com.demo.dao.OperLogDao;
import com.demo.entity.OperateLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//@Component
@Aspect
@Component
//@AllArgsConstructor
public class OperateAspect {

    /**
     * 1.定义切入点
     * 2.横切逻辑
     * 3.织入 (spring自动处理)
     */

    private final OperLogDao operLogDao;

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            1, 1, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100)
    );

    public OperateAspect(OperLogDao operLogDao) {
        this.operLogDao = operLogDao;
    }

    @AfterReturning(value = "@annotation(com.demo.anno.OperateRecord)" , returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) throws Throwable {
        threadPoolExecutor.execute(() -> {
            try {
                OperateLog operateLog = new OperateLog();
                operateLog.setOperTime(new Date());
                operateLog.setOperName("jack");

                MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                OperateRecord annotation = methodSignature.getMethod().getAnnotation(OperateRecord.class);
                operateLog.setOperType(annotation.desc());
                operateLog.setResult(String.valueOf(result));

                operLogDao.save(operateLog);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}

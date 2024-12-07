package com.bite.book.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TimeRecordAspect {
    /**
     * 记录耗时
     */
    @Around("execution(* com.bite.book.controller.*.*(..))")
    public Object timeRecord(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录开始时间
        long start = System.currentTimeMillis();
        //执行目标方法
        Object proceed = joinPoint.proceed();
        //记录结束时间
        long end = System.currentTimeMillis();
        //日志打印耗时
        log.info("耗时时间: "+ (end-start) + "ms");
        return proceed;
    }


}

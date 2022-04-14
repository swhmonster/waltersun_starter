package com.waltersun.waltersunspringbootstarter.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.waltersun.waltersunspringbootstarter.annotation.StandardMonitorLog;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 接口出入参规范化日志切面
 *
 * @author 道禹 Walter
 * @date 2022-04-11
 */
@Component
@Aspect
@Slf4j
public class StandardLogAspect {
    @Pointcut("@annotation(com.aliexpress.component.annotation.StandardLog)")
    private void pointCut() {
    }

    @Around("pointCut()")
    @SneakyThrows
    public Object printStandardLog(ProceedingJoinPoint proceedingJoinPoint) {
        Object[] args = proceedingJoinPoint.getArgs();
        String methodName = proceedingJoinPoint.getSignature().getName();
        log.info("[traceId:{}][function input][{}]:{}","traceId", methodName, args);
        Object obj = proceedingJoinPoint.proceed();
        log.info("[traceId:{}][function output][{}]:{}", "traceId", methodName, JSON.toJSONString(obj));
        return obj;
    }

    @Pointcut("@annotation(com.aliexpress.component.annotation.StandardMonitorLog)")
    private void monitorPointCut() {
    }

    @Around("monitorPointCut()")
    @SneakyThrows
    public Object printStandardMonitorLog(ProceedingJoinPoint proceedingJoinPoint) {
        // get annotation params
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        StandardMonitorLog annotation = signature.getMethod().getAnnotation(StandardMonitorLog.class);
        String hsfType = annotation.type();

        // get method name
        String methodName = proceedingJoinPoint.getSignature().getName();

        log.info("[traceId:{}][hsf-{}-request][{}]", "traceId", hsfType, methodName);
        Object obj = proceedingJoinPoint.proceed();
        log.info("[traceId:{}][hsf-{}-response][{}]", "traceId", hsfType, methodName);
        return obj;
    }
}

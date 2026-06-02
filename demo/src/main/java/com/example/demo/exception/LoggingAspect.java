package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LoggingAspect {
    // Áp dụng cho tất cả các phương thức trong package service
    @AfterThrowing(pointcut = "execution(* com.example.demo.service..*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex){
        log.error("Lỗi tại phương thức {}: {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}

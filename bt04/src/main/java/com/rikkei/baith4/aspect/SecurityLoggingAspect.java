package com.rikkei.baith4.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(SecurityLoggingAspect.class);

    // Bắt toàn bộ ngoại lệ ném ra từ các class trong gói com.example.elearning.service
    @AfterThrowing(pointcut = "execution(* com.rikkei.baith4.service..*(..))", throwing = "ex")
    public void logServiceException(JoinPoint joinPoint, Throwable ex) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        // Ghi log bảo mật chi tiết ra Console hệ thống
        logger.error("[SYSTEM SECURITY LOG] Ngoại lệ xảy ra tại [Class: {}] -> [Method: {}] | Lý do: {}",
                className, methodName, ex.getMessage());
    }
}
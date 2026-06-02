package com.rikkei.baith2.aspect;

import com.rikkei.baith2.entity.ErrorLog;
import com.rikkei.baith2.repository.ErrorLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAspect {

    private final ErrorLogRepository errorLogRepository;

    public LoggingAspect(ErrorLogRepository errorLogRepository) {
        this.errorLogRepository = errorLogRepository;
    }


    @AfterThrowing(pointcut = "execution(* com.rikkei.baith2.service.*.*(..))", throwing = "exception")
    public void logServiceFailure(JoinPoint joinPoint, Throwable exception) {
        ErrorLog errorLog = new ErrorLog();
        errorLog.setTimestamp(LocalDateTime.now());
        errorLog.setMethodName(joinPoint.getSignature().toShortString());
        errorLog.setExceptionMessage(exception.getMessage());

        errorLogRepository.save(errorLog);
    }
}
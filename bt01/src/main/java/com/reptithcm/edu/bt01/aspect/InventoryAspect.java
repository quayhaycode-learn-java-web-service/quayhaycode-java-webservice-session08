package com.reptithcm.edu.bt01.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class InventoryAspect {

    // 1. Security Check
    @Before("execution(* com.reptithcm.edu.bt01.service.InventoryService.deleteProduct(..)) && args(id)")
    public void checkAdmin(JoinPoint jp) {
        String role = getHeader("X-Role"); // Giả lập lấy từ RequestContextHolder
        if (!"ADMIN".equals(role)) throw new SecurityException("Access Denied!");
    }

    // 2. Activity Logging
    @AfterReturning(value = "execution(* com.reptithcm.edu.bt01.service.InventoryService.stock*(..)) && args(sku, quantity)", argNames = "sku,quantity")
    public void logActivity(String sku, int quantity) {
        String user = getHeader("X-User");
        log.info("User: {} performed action successfully. Quantity changed: {}", user, quantity);
        // Lưu vào bảng InventoryLog bằng repository.save(...)
    }

    // 3. Performance Tracking
    @Around("execution(* com.reptithcm.edu.bt01.service.InventoryService.inspectInventory(..))")
    public Object trackTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        log.info("Execution time: {}ms", System.currentTimeMillis() - start);
        return proceed;
    }

    private String getHeader(String key) {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(key);
    }
}

package com.rikkei.baith5.aspect;

import com.rikkei.baith5.dto.OrderRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class LoggingAspect {
    @Before("execution(* com.rikkei.baith5.service.PlaceOrderService.placeOrder(..))")
    public void logRawOrder(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String username = (String) args[0];
        OrderRequest request = (OrderRequest) args[1];
        System.out.println("[LOG TẦNG ĐẦU VÀO] User " + username + " đang gửi request đặt lệnh: " + request);
    }
}
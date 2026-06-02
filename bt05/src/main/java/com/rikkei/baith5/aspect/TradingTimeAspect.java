package com.rikkei.baith5.aspect;

import com.rikkei.baith5.exception.MarketClosedException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.time.LocalTime;

@Aspect
@Component
@Order(2)
public class TradingTimeAspect {
    @Before("execution(* com.rikkei.baith5.service.PlaceOrderService.placeOrder(..))")
    public void checkTradingTime() {
        LocalTime now = LocalTime.now();
        LocalTime openTime = LocalTime.of(9, 0);
        LocalTime closeTime = LocalTime.of(15, 0);

        if (now.isBefore(openTime) || now.isAfter(closeTime)) {
            throw new MarketClosedException("Hệ thống từ chối! Sàn chứng khoán hiện đang đóng cửa (Giờ mở cửa: 09:00 - 15:00).");
        }
    }
}
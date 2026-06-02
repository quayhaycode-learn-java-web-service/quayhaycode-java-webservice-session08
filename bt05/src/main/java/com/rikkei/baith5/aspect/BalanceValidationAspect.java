package com.rikkei.baith5.aspect;

import com.rikkei.baith5.dto.OrderRequest;
import com.rikkei.baith5.entity.AccountBalance;
import com.rikkei.baith5.exception.InsufficientFundsException;
import com.rikkei.baith5.repository.AccountBalanceRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class BalanceValidationAspect {

    @Autowired
    private AccountBalanceRepository balanceRepository;

    @Before("execution(* com.trading.engine.service.PlaceOrderService.placeOrder(..))")
    public void validateBalance(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String username = (String) args[0];
        OrderRequest request = (OrderRequest) args[1];

        // Lệnh Mua (BUY) mới cần check số dư tiền mặt
        if ("BUY".equalsIgnoreCase(request.getOrderType())) {
            AccountBalance balance = balanceRepository.findById(username)
                    .orElseThrow(() -> new InsufficientFundsException("Tài khoản nhà đầu tư không tồn tại trên hệ thống."));

            double requiredCash = request.getQuantity() * request.getPrice();
            if (balance.getCashAvailable() < requiredCash) {
                throw new InsufficientFundsException("Đặt lệnh thất bại! Số dư không đủ. Cần có: "
                        + requiredCash + " nhưng hiện tại chỉ có: " + balance.getCashAvailable());
            }
        }
    }
}
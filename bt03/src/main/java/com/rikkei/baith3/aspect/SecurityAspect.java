package com.rikkei.baith3.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class SecurityAspect {

    // Pointcut sử dụng @annotation đảm bảo tính đóng gói
    @Before("@annotation(com.rikkei.baith3.annotation.RequireOtp)")
    public void validateOtp() {
        HttpServletRequest request = getCurrentRequest();
        String otp = request.getHeader("X-OTP");

        if (otp == null || !otp.equals("123456")) {
            throw new SecurityException("Transaction denied: Invalid or missing OTP.");
        }
    }

    @Before("@annotation(com.rikkei.baith3.annotation.RequireManagerApproval)")
    public void validateManagerRole() {
        HttpServletRequest request = getCurrentRequest();
        String role = request.getHeader("X-Role");

        if (role == null || !role.equalsIgnoreCase("MANAGER")) {
            throw new SecurityException("Transaction denied: Unauthorized role. MANAGER privileges required.");
        }
    }

    private HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attributes.getRequest();
    }
}
package com.rikkei.baith3.service;

import com.rikkei.baith3.dto.PaymentRequest;
import com.rikkei.baith3.dto.RefundRequest;
import com.rikkei.baith3.annotation.RequireOtp;
import com.rikkei.baith3.annotation.RequireManagerApproval;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public void processDomesticPayment(PaymentRequest request) {
        System.out.println("Processing domestic payment: " + request.getAmount() + " " + request.getCurrency());
    }

    @RequireOtp
    public void processInternationalPayment(PaymentRequest request) {
        System.out.println("Processing international payment: " + request.getAmount() + " " + request.getCurrency());
    }

    @RequireManagerApproval
    public void processRefund(RefundRequest request) {
        System.out.println("Processing refund for code: " + request.getTransactionCode() + " with amount: " + request.getAmount());
    }
}
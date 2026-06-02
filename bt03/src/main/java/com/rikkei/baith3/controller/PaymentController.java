package com.rikkei.baith3.controller;

import com.rikkei.baith3.dto.PaymentRequest;
import com.rikkei.baith3.dto.RefundRequest;
import com.rikkei.baith3.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/domestic")
    public ResponseEntity<String> domestic(@RequestBody PaymentRequest request) {
        paymentService.processDomesticPayment(request);
        return ResponseEntity.ok("Domestic payment processed successfully.");
    }

    @PostMapping("/international")
    public ResponseEntity<String> international(@RequestBody PaymentRequest request) {
        paymentService.processInternationalPayment(request);
        return ResponseEntity.ok("International payment processed successfully.");
    }

    @PostMapping("/refund")
    public ResponseEntity<String> refund(@Valid @RequestBody RefundRequest request) {
        paymentService.processRefund(request);
        return ResponseEntity.ok("Refund processed successfully.");
    }
}
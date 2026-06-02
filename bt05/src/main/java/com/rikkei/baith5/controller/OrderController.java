package com.rikkei.baith5.controller;

import com.rikkei.baith5.dto.OrderRequest;
import com.rikkei.baith5.entity.StockOrder;
import com.rikkei.baith5.service.PlaceOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private PlaceOrderService placeOrderService;

    @PostMapping("/place")
    public ResponseEntity<StockOrder> placeOrder(
            @RequestHeader("X-User") String username,
            @Valid @RequestBody OrderRequest request) {

        StockOrder dynamicOrder = placeOrderService.placeOrder(username, request);
        return ResponseEntity.ok(dynamicOrder);
    }
}
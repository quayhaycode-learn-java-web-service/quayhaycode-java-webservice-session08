package com.reptithcm.edu.bt01.controller;

import com.reptithcm.edu.bt01.dto.StockDto;
import com.reptithcm.edu.bt01.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor

public class InventoryController {

    private static InventoryService service;

    @PostMapping("/stock-in")
    public ResponseEntity<?> stockIn(@RequestBody StockDto dto,
                                     @RequestHeader("X-User") String user) {
        service.stockIn(dto.getSku(), dto.getQuantity());
        return ResponseEntity.ok("Success");
    }


}

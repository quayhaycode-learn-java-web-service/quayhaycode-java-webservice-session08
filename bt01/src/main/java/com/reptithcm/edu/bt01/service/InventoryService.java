package com.reptithcm.edu.bt01.service;

import com.reptithcm.edu.bt01.entity.Product;
import com.reptithcm.edu.bt01.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final ProductRepository repository;

    @Transactional
    public void stockIn(String sku, int quantity) {
        repository.updateQuantity(sku, quantity);
    }

    @Transactional
    public void stockOut(String sku, int quantity) {
        Product p = repository.findBySku(sku).orElseThrow();
        if (p.getQuantity() < quantity) throw new RuntimeException("Không đủ hàng!");
        repository.updateQuantity(sku, -quantity);
    }

    public void inspectInventory() {}

    public void deleteProduct(Long id) { repository.deleteById(id); }
}
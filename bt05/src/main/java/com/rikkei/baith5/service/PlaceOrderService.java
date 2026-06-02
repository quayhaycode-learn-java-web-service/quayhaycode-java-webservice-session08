package com.rikkei.baith5.service;

import com.rikkei.baith5.dto.OrderRequest;
import com.rikkei.baith5.entity.StockOrder;
import com.rikkei.baith5.exception.MarginViolationException;
import com.rikkei.baith5.repository.StockOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlaceOrderService {

    @Autowired
    private StockOrderRepository orderRepository;

    private static final double REFERENCE_PRICE = 100.0;
    private static final double MARGIN_PERCENT = 0.07;

    @Transactional
    public StockOrder placeOrder(String username, OrderRequest request) {
        // Kiểm tra biên độ giá trần/sàn (7%)
        double lowerBound = REFERENCE_PRICE * (1 - MARGIN_PERCENT); // 93.0
        double upperBound = REFERENCE_PRICE * (1 + MARGIN_PERCENT); // 107.0

        if (request.getPrice() < lowerBound || request.getPrice() > upperBound) {
            throw new MarginViolationException("Vi phạm biên độ giá! Giá đặt mua (" + request.getPrice()
                    + ") vượt quá 7% so với giá tham chiếu " + REFERENCE_PRICE + " (Vùng cho phép: " + lowerBound + " - " + upperBound + ")");
        }

        StockOrder order = new StockOrder();
        order.setStockCode(request.getStockCode());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        order.setOrderType(request.getOrderType());

        return orderRepository.save(order);
    }
}
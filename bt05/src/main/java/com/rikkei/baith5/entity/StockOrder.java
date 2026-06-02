package com.rikkei.baith5.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StockOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stockCode;
    private Integer quantity;
    private Double price;
    private String orderType;
}
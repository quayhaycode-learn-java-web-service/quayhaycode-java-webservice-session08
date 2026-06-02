package com.rikkei.baith5.repository;

import com.rikkei.baith5.entity.StockOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockOrderRepository extends JpaRepository<StockOrder, Long> {}
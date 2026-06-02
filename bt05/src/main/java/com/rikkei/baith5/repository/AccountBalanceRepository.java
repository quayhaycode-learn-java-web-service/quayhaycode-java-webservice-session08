package com.rikkei.baith5.repository;

import com.rikkei.baith5.entity.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, String> {
    
}

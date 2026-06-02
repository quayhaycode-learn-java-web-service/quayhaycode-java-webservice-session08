package com.rikkei.baith5.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AccountBalance {
    @Id
    private String username;
    private Double cashAvailable;
}
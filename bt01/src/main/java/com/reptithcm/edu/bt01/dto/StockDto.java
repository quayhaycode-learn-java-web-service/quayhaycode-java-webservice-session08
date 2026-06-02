package com.reptithcm.edu.bt01.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class StockDto {
    private String sku;
    private Integer quantity;
}
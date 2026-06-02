package com.rikkei.baith5.dto;

import com.rikkei.baith5.validation.LotSize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class OrderRequest {

    @NotNull(message = "Mã chứng khoán không được để trống")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Mã chứng khoán phải viết hoa toàn bộ và có đúng 3 ký tự (Ví dụ: VNM)")
    private String stockCode;

    @NotNull(message = "Số lượng không được để trống")
    @LotSize
    private Integer quantity;

    @NotNull(message = "Giá không được để trống")
    @Min(value = 1, message = "Giá đặt mua phải lớn hơn 0")
    private Double price;

    @NotNull(message = "Loại lệnh không được để trống")
    @Pattern(regexp = "^(BUY|SELL)$", message = "Loại lệnh phải là BUY hoặc SELL")
    private String orderType;
}
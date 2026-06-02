package com.rikkei.baith3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefundRequest {
    @NotBlank(message = "Transaction code cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$", message = "Invalid transaction code. Malicious patterns detected!")
    private String transactionCode;

    private Double amount;
}

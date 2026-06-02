package com.rikkei.baith2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
    @NotBlank(message = "So hieu chuyen bay khong dc de trong")
    public String flightNumber;

    @NotBlank(message = "Ten hanh khach khong dc de trong hoac chi chua khoang trang")
    public String passengerName;
}

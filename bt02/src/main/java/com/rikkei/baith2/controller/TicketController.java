package com.rikkei.baith2.controller;

import com.rikkei.baith2.dto.BookingRequest;
import com.rikkei.baith2.entity.Ticket;
import com.rikkei.baith2.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final BookingService bookingService;

    public TicketController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<Ticket> bookTicket(@Valid @RequestBody BookingRequest request) {
        // Tên hành khách truyền vào đây sẽ đi qua tầng Aspect xử lý khoảng trắng thừa
        Ticket ticket = bookingService.bookTicket(request.getFlightNumber(), request.getPassengerName());
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/cancel/{ticketId}")
    public ResponseEntity<String> cancelTicket(@PathVariable Long ticketId) {
        bookingService.cancelTicket(ticketId);
        return ResponseEntity.ok("Hủy vé thành công.");
    }
}
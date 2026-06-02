package com.rikkei.baith2.service;

import com.rikkei.baith2.entity.Ticket;

public interface BookingService {
    Ticket bookTicket(String flightNumber, String passengerName);
    void cancelTicket(Long ticketId);
}
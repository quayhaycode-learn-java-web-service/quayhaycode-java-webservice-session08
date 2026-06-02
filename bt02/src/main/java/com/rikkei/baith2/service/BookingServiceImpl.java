package com.rikkei.baith2.service;

import com.rikkei.baith2.entity.Flight;
import com.rikkei.baith2.entity.Ticket;
import com.rikkei.baith2.repository.FlightRepository;
import com.rikkei.baith2.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingServiceImpl implements BookingService {
    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;

    public BookingServiceImpl(FlightRepository flightRepository, TicketRepository ticketRepository) {
        this.flightRepository = flightRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    public Ticket bookTicket(String flightNumber, String passengerName) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chuyến bay có mã: " + flightNumber));
        if(flight.getAvailableSeats() <= 0) {
            throw new RuntimeException("Chuyen bay da het ghe trong!");
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightRepository.save(flight);

        Ticket ticket = new Ticket();
        ticket.setFlightId(flight.getId());
        ticket.setPassengerName(passengerName);
        ticket.setStatus("BOOKED");

        return ticketRepository.save(ticket);
    }


    @Override
    @Transactional
    public void cancelTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay ve co ID: " + ticketId));

        if("CANCELED".equals(ticket.getStatus())) {
            throw new RuntimeException("Ve nay da duoc huy trc do!");
        }

        Flight flight = flightRepository.findById(ticket.getFlightId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin chuyến bay của vé này"));

        flight.setAvailableSeats(flight.getAvailableSeats() + 1);
        flightRepository.save(flight);

        ticket.setStatus("CANCELED");
        ticketRepository.save(ticket);
    }
}

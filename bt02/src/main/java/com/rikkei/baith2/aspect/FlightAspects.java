package com.rikkei.baith2.aspect;

import com.rikkei.baith2.entity.Flight;
import com.rikkei.baith2.entity.Ticket;
import com.rikkei.baith2.repository.FlightRepository;
import com.rikkei.baith2.repository.TicketRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Component
public class FlightAspects {

    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;

    public FlightAspects(TicketRepository ticketRepository, FlightRepository flightRepository) {
        this.ticketRepository = ticketRepository;
        this.flightRepository = flightRepository;
    }


    @Around("execution(* com.rikkei.baith2.service.BookingService.bookTicket(..))")
    public Object sanitizePassengerName(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();


        if (args != null && args.length >= 2 && args[1] instanceof String) {
            String rawPassengerName = (String) args[1];
            if (rawPassengerName != null) {
                String sanitizedName = rawPassengerName.trim().toUpperCase();
                args[1] = sanitizedName; // Ghi đè lại vào mảng tham số
            }
        }

        return joinPoint.proceed(args);
    }


    @Before("execution(* com.rikkei.baith2.service.BookingService.cancelTicket(..)) && args(ticketId)")
    public void validateCancelTimeRule(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vé để kiểm tra điều kiện hủy"));

        Flight flight = flightRepository.findById(ticket.getFlightId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin chuyến bay gắn liền với vé"));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime departureTime = flight.getDepartureTime();

        long hoursUntilDeparture = Duration.between(now, departureTime).toHours();

        if (hoursUntilDeparture < 24) {
            throw new RuntimeException("Vi phạm quy định: Không thể hủy vé sát giờ bay (Thời gian còn lại < 24 giờ)!");
        }
    }
}
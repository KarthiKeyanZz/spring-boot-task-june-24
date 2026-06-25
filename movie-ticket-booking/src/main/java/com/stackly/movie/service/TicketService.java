package com.stackly.movie.service;

import com.stackly.movie.dto.SeatUpdateRequest;
import com.stackly.movie.dto.TicketRequest;
import com.stackly.movie.exception.FieldValidationException;
import com.stackly.movie.exception.SeatAlreadyBookedException;
import com.stackly.movie.exception.TicketNotFoundException;
import com.stackly.movie.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TicketService {

    private static final double MAX_TICKET_PRICE = 5000.0;

    private final Map<Long, Ticket> store = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(0);

    public Ticket create(TicketRequest request) {
        validatePrice(request.getTicketPrice());
        requireSeatFree(request.getMovieName(), request.getSeatNumber(), null);
        long id = idSequence.incrementAndGet();
        Ticket ticket = new Ticket(id, request.getMovieName(), request.getCustomerName(),
                request.getSeatNumber(), request.getTicketPrice());
        store.put(id, ticket);
        return ticket;
    }

    public List<Ticket> findAll() {
        return new ArrayList<>(store.values());
    }

    public Ticket findById(Long id) {
        Ticket ticket = store.get(id);
        if (ticket == null) {
            throw new TicketNotFoundException(id);
        }
        return ticket;
    }

    public Ticket update(Long id, TicketRequest request) {
        Ticket ticket = findById(id);
        validatePrice(request.getTicketPrice());
        requireSeatFree(request.getMovieName(), request.getSeatNumber(), id);
        ticket.setMovieName(request.getMovieName());
        ticket.setCustomerName(request.getCustomerName());
        ticket.setSeatNumber(request.getSeatNumber());
        ticket.setTicketPrice(request.getTicketPrice());
        return ticket;
    }

    public Ticket updateSeat(Long id, SeatUpdateRequest request) {
        Ticket ticket = findById(id);
        requireSeatFree(ticket.getMovieName(), request.getSeatNumber(), id);
        ticket.setSeatNumber(request.getSeatNumber());
        return ticket;
    }

    public void delete(Long id) {
        if (store.remove(id) == null) {
            throw new TicketNotFoundException(id);
        }
    }

    private void validatePrice(Double price) {
        if (price != null && price > MAX_TICKET_PRICE) {
            throw new FieldValidationException("Ticket price cannot exceed " + MAX_TICKET_PRICE);
        }
    }

    private void requireSeatFree(String movieName, String seatNumber, Long excludeId) {
        boolean taken = store.values().stream()
                .filter(t -> !t.getTicketId().equals(excludeId))
                .anyMatch(t -> t.getMovieName().equalsIgnoreCase(movieName)
                        && t.getSeatNumber().equalsIgnoreCase(seatNumber));
        if (taken) {
            throw new SeatAlreadyBookedException(seatNumber, movieName);
        }
    }
}

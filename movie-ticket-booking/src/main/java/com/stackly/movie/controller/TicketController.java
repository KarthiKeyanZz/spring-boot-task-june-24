package com.stackly.movie.controller;

import com.stackly.movie.dto.SeatUpdateRequest;
import com.stackly.movie.dto.TicketRequest;
import com.stackly.movie.model.Ticket;
import com.stackly.movie.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@Tag(name = "Movie Ticket Booking", description = "Book and manage movie tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Operation(summary = "Book a new movie ticket")
    @PostMapping
    public ResponseEntity<Ticket> create(@Valid @RequestBody TicketRequest request) {
        Ticket created = ticketService.create(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "View all booked tickets")
    @GetMapping
    public ResponseEntity<List<Ticket>> getAll() {
        return ResponseEntity.ok(ticketService.findAll());
    }

    @Operation(summary = "Find a ticket by id")
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @Operation(summary = "Fully update a ticket")
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@PathVariable Long id, @Valid @RequestBody TicketRequest request) {
        return ResponseEntity.ok(ticketService.update(id, request));
    }

    @Operation(summary = "Change the seat of a booked ticket")
    @PatchMapping("/{id}/seat")
    public ResponseEntity<Ticket> updateSeat(@PathVariable Long id, @Valid @RequestBody SeatUpdateRequest request) {
        return ResponseEntity.ok(ticketService.updateSeat(id, request));
    }

    @Operation(summary = "Cancel a ticket")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

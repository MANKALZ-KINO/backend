package com.example.backend.controllers;

import com.example.backend.model.MoviePlan;
import com.example.backend.model.Seat;
import com.example.backend.model.Ticket;
import com.example.backend.repositories.IMoviePlanRepository;
import com.example.backend.repositories.ISeatRepository;
import com.example.backend.service.TicketService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final IMoviePlanRepository moviePlanRepository;
    private final ISeatRepository seatRepository;

    public TicketController(TicketService ticketService, IMoviePlanRepository moviePlanRepository, ISeatRepository seatRepository) {
        this.ticketService = ticketService;
        this.moviePlanRepository = moviePlanRepository;
        this.seatRepository = seatRepository;
    }

    // GET: Hent alle
    @GetMapping("/all")
    public List<Ticket> getAllTickets() {
        return ticketService.findAllTickets();
    }

    @PostMapping("/createTicket")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Seat seat = seatRepository.findById(ticket.getSeat().getSeatId())
                .orElseThrow(() -> new RuntimeException("Seat not found!"));
        ticket.setSeat(seat);

        MoviePlan moviePlan = moviePlanRepository.findById(ticket.getMoviePlan().getMoviePlanId())
                .orElseThrow(() -> new RuntimeException("MoviePlan not found!"));
        ticket.setMoviePlan(moviePlan); // Sikrer at moviePlan indeholder en movie

        Ticket savedTicket = ticketService.saveTicket(ticket);
        return ResponseEntity.ok(savedTicket);
    }


    //getticketbyid
    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> findByTicketId(@PathVariable Long ticketId) {
        Optional<Ticket> ticket = ticketService.findTicketById(ticketId);
        return ticket.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    // UPDATE
    @PutMapping("/update/{ticketId}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long ticketId, @RequestBody Ticket updatedTicket) {
        if (!ticketService.existsById(ticketId)) {
            return ResponseEntity.notFound().build();
        }
        updatedTicket.setTicketID(ticketId);
        Ticket savedTicket = ticketService.saveTicket(updatedTicket);
        return ResponseEntity.ok(savedTicket);
    }

    //DELETE via id
    @DeleteMapping("/delete/{ticketId}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        if (!ticketService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ticketService.deleById(id);
        return ResponseEntity.ok("Ticket deleted successfully");
    }

}

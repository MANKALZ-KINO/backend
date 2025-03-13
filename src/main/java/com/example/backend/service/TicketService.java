package com.example.backend.service;

import com.example.backend.dtos.TicketDTO;
import com.example.backend.model.Ticket;
import com.example.backend.repositories.IMoviePlanRepository;
import com.example.backend.repositories.ITicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.backend.model.MoviePlan;
import com.example.backend.model.Seat;
import com.example.backend.model.Ticket;
import com.example.backend.repositories.IMoviePlanRepository;
import com.example.backend.repositories.ISeatRepository;
import com.example.backend.repositories.ITicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final ITicketRepository ticketRepository;
    private final IMoviePlanRepository moviePlanRepository;
    private final ISeatRepository seatRepository;

    public TicketService(ITicketRepository ticketRepository, IMoviePlanRepository moviePlanRepository, ISeatRepository seatRepository) {
        this.ticketRepository = ticketRepository;
        this.moviePlanRepository = moviePlanRepository;
        this.seatRepository = seatRepository;
    }

    public int displayAgeWithinLimit(Ticket ticket) {
        int ageLimit = ticket.getMoviePlan().getMovie().getAgeLimit();
        return ageLimit;
    }

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> findTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    // Fixed syntax error in existsById method
    public boolean existsById(Long id) {
        return ticketRepository.existsById(id);
    }

    public List<Ticket> getTicketsByPhoneNumber(int phoneNumber) {
        return ticketRepository.findByPhoneNumber(phoneNumber);
    }

    // Added null check in cancelTicket method
    public void cancelTicket(Long ticketId) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);

        if (!ticketOpt.isPresent()) {
            throw new EntityNotFoundException("Ticket not found with id: " + ticketId);
        }

        Ticket ticket = ticketOpt.get();
        ticketRepository.delete(ticket);
    }

    public Ticket changeTicket(Long ticketId, Long newSeatId, Long newMoviePlanId) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);

        if (!ticketOpt.isPresent()) {
            throw new EntityNotFoundException("Ticket not found with id: " + ticketId);
        }

        Ticket ticket = ticketOpt.get();

        Optional<Seat> seatOpt = seatRepository.findById(newSeatId);

        if (!seatOpt.isPresent()) {
            throw new EntityNotFoundException("Seat not found with id: " + newSeatId);
        }

        Seat seat = seatOpt.get();

        List<Ticket> existingTickets = ticketRepository.findBySeat_SeatId(newSeatId);

        for (Ticket existingTicket : existingTickets) {
            if (existingTicket.getMoviePlan().getMoviePlanId().equals(newMoviePlanId) &&
                    !existingTicket.getTicketID().equals(ticketId)) {
                throw new IllegalArgumentException("The selected seat is already taken for this showing");
            }
        }

        ticket.setSeat(seat);

        Optional<MoviePlan> moviePlanOpt = moviePlanRepository.findById(newMoviePlanId);

        if (!moviePlanOpt.isPresent()) {
            throw new EntityNotFoundException("Movie plan not found with id: " + newMoviePlanId);
        }

        MoviePlan moviePlan = moviePlanOpt.get();
        ticket.setMoviePlan(moviePlan);

        return ticketRepository.save(ticket);
    }

    public Ticket saveTicket(Ticket updatedTicket) {
        return ticketRepository.save(updatedTicket);
    }

    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }
}


package com.example.backend.service;

import com.example.backend.model.Ticket;
import com.example.backend.repositories.IMoviePlanRepository;
import com.example.backend.repositories.ITicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final ITicketRepository ticketRepository;
    private final IMoviePlanRepository moviePlanRepository;

    public TicketService(ITicketRepository ticketRepository, IMoviePlanRepository moviePlanRepository) {
        this.ticketRepository = ticketRepository;
        this.moviePlanRepository = moviePlanRepository;
    }

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findTicketById(Long id) {
         return ticketRepository.findById(Math.toIntExact(id));
    }

    public Ticket saveTicket(Ticket updatedTicket) {
      return ticketRepository.save(updatedTicket);
    }
    public void deleById(Long id) {
        ticketRepository.deleteById(Math.toIntExact(id));
    }
}

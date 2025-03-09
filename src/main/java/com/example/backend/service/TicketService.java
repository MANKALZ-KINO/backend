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

    //ved ikke om denne skal bruges
    public int displayAgeWithinLimit(Ticket ticket) {
        int ageLimit = ticket.getMoviePlan().getMovie().getAgeLimit();
        return ageLimit;
    }
    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }
    public void createTicket(Ticket ticket) {
         ticketRepository.save(ticket);
    }
    public Optional<Ticket> findTicketById(Long id) {
         return ticketRepository.findById(Math.toIntExact(id));
    }

    public boolean existsById(Long id) {
      if (ticketRepository.existsById(Math.toIntExact(id))) {
          return true;
      } else {
          return false;
      }
    }

    public Ticket saveTicket(Ticket updatedTicket) {
      return ticketRepository.save(updatedTicket);
    }
    public void deleById(Long id) {
        ticketRepository.deleteById(Math.toIntExact(id));
    }

}

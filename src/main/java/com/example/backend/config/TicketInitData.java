package com.example.backend.config;

import com.example.backend.model.MoviePlan;
import com.example.backend.model.Seat;
import com.example.backend.model.Ticket;
import com.example.backend.repositories.IMoviePlanRepository;
import com.example.backend.repositories.ISeatRepository;
import com.example.backend.repositories.ITicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class TicketInitData implements CommandLineRunner {

    private final ITicketRepository iTicketRepository;
    private final ISeatRepository iSeatRepository;
    private final IMoviePlanRepository iMoviePlanRepository;

    public TicketInitData(ITicketRepository iTicketRepository, ISeatRepository iSeatRepository, IMoviePlanRepository iMoviePlanRepository) {
        this.iTicketRepository = iTicketRepository;
        this.iSeatRepository = iSeatRepository;
        this.iMoviePlanRepository = iMoviePlanRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (iTicketRepository.count() == 0) {
            // Hent eksisterende sæder
            List<Seat> seats = iSeatRepository.findAll();

            if (seats.size() < 2) {
                System.out.println("Der skal være mindst to sæder i databasen for at oprette billetter.");
                return;
            }

            // Hent en eksisterende MoviePlan fra databasen
            Optional<MoviePlan> optionalMoviePlan = iMoviePlanRepository.findById(1L); //L for long

            if (optionalMoviePlan.isEmpty()) {
                System.out.println("Fejl: Ingen MoviePlan fundet! Billetter kan ikke oprettes.");
                return;
            }

            MoviePlan moviePlan = optionalMoviePlan.get();

            Ticket ticket1 = new Ticket();
            ticket1.setOrder_date(LocalDate.now());
            ticket1.setTicket_price(120.50);
            ticket1.setPhoneNumber(12345678);
            ticket1.setSeat(seats.get(2));
            ticket1.setMoviePlan(moviePlan);

            Ticket ticket2 = new Ticket();
            ticket2.setOrder_date(LocalDate.now());
            ticket2.setTicket_price(99.99);
            ticket2.setPhoneNumber(87654321);
            ticket2.setSeat(seats.get(1));
            ticket2.setMoviePlan(moviePlan);

            iTicketRepository.save(ticket1);
            iTicketRepository.save(ticket2);



        }
    }
}

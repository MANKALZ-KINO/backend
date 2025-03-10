package com.example.backend.controllers;

import com.example.backend.model.FreeSeats;
import com.example.backend.model.Seat;
import com.example.backend.repositories.FreeSeatsRepository;
import com.example.backend.service.SeatService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class FreeSeatsController {
    private FreeSeatsRepository freeSeatsRepository;
    private SeatService seatService;

    public FreeSeatsController(FreeSeatsRepository freeSeatsRepository, SeatService seatService) {
        this.freeSeatsRepository = freeSeatsRepository;
        this.seatService = seatService;
    }

    @GetMapping("/allFreeSeats")
    public List<FreeSeats> getFreeSeats() {
        List<FreeSeats> seats = freeSeatsRepository.findAll();
        return seats;
    }

    @GetMapping("/allFreeSeats/{id}")
    public List<FreeSeats> getFreeSeatsById(@PathVariable Long id) {
        List<FreeSeats> seats = freeSeatsRepository.findBymoviePlanId(id);
        return seats;
    }
    @GetMapping("/allSeats")
    public List<Seat> getAllSeats() {
        List<Seat> seats = seatService.getAllSeats();
        return seats;
    }
}

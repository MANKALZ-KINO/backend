package com.example.backend.controllers;

import com.example.backend.model.FreeSeats;
import com.example.backend.model.Seat;
import com.example.backend.repositories.FreeSeatsRepository;
import com.example.backend.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public FreeSeatsController(FreeSeatsRepository freeSeatsRepository, SeatService seatService) {
        this.freeSeatsRepository = freeSeatsRepository;
        this.seatService = seatService;
    }

    @GetMapping("/allFreeSeats")
    public List<FreeSeats> getFreeSeats() {
        List<FreeSeats> seats = freeSeatsRepository.findAll();
        return seats;
    }

    @GetMapping("/allFreeSeats/{moviePlanId}")
    public List<FreeSeats> getFreeSeatsById(@PathVariable Long moviePlanId) {
        List<FreeSeats> seats = freeSeatsRepository.findBymoviePlanId(moviePlanId);
        return seats;
    }
    @GetMapping("/allSeats")
    public List<Seat> getAllSeats() {
        List<Seat> seats = seatService.getAllSeats();
        return seats;
    }
}

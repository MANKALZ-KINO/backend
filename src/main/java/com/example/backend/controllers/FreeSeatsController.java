package com.example.backend.controllers;

import com.example.backend.model.FreeSeats;
import com.example.backend.repositories.FreeSeatsRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class FreeSeatsController {
    private FreeSeatsRepository freeSeatsRepository;

    public FreeSeatsController(FreeSeatsRepository freeSeatsRepository) {
        this.freeSeatsRepository = freeSeatsRepository;
    }

    @GetMapping("/allseats")
    public List<FreeSeats> getFreeSeats() {
        List<FreeSeats> seats = freeSeatsRepository.findAll();
        return seats;
    }

    @GetMapping("/allseats/{id}")
    public List<FreeSeats> getFreeSeatsById(@PathVariable Long id) {
        List<FreeSeats> seats = freeSeatsRepository.findBymoviePlanId(id);
        return seats;
    }
}

package com.example.backend.service;

import com.example.backend.model.Seat;
import com.example.backend.repositories.ISeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private ISeatRepository iSeatRepository;

    public SeatService(ISeatRepository iSeatRepository) {
        this.iSeatRepository = iSeatRepository;
    }

    public List<Seat> getAllSeats() {
        return iSeatRepository.findAll();
    }
}

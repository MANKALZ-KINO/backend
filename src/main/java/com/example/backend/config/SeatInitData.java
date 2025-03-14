package com.example.backend.config;

import com.example.backend.model.Seat;
import com.example.backend.model.Theater;
import com.example.backend.repositories.ISeatRepository;
import com.example.backend.repositories.ITheaterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeatInitData implements CommandLineRunner {


    @PersistenceContext
    private EntityManager entityManager;

    private final ISeatRepository iSeatRepository;
    private final ITheaterRepository iTheaterRepository;

    public SeatInitData(ISeatRepository iSeatRepository, ITheaterRepository iTheaterRepository) {
        this.iSeatRepository = iSeatRepository;
        this.iTheaterRepository = iTheaterRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<Theater> theaters = iTheaterRepository.findAll();

        List<Seat> seats = new ArrayList<>();

        for (Theater theater : theaters) {
            Theater managedTheater = entityManager.merge(theater);
            int numRows;
            int numSeatsPerRow;

            if (managedTheater.getCapacity() <= 10) {
                numRows = 2;
                numSeatsPerRow = 5;
            } else {
                numRows = 2;
                numSeatsPerRow = 6;
            }

            for (int row = 1; row <= numRows; row++) {
                for (int seatNum = 1; seatNum <= numSeatsPerRow; seatNum++) {
                    Seat seat = new Seat();
                    seat.setRowNum(row);
                    seat.setSeatNumb(seatNum);
                    seat.setTheater(managedTheater);
                    seats.add(seat);
                }
            }
            System.out.println((numRows * numSeatsPerRow) + " seats added for Theater ID: " + managedTheater.getTheaterId());
        }

        iSeatRepository.saveAll(seats);
        System.out.println("All seats initialized successfully!");
    }
}


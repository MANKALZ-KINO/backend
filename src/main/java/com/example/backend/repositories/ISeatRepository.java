package com.example.backend.repositories;

import com.example.backend.model.Seat;
import com.example.backend.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ISeatRepository extends JpaRepository<Seat, Long> {
    boolean existsByRowNumAndSeatNumbAndTheater(int rowNum, int seatNumb, Theater theater);

}

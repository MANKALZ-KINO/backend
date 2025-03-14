package com.example.backend.repositories;

import com.example.backend.model.FreeSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreeSeatsRepository extends JpaRepository<FreeSeats, Long> {
    List<FreeSeats> findBymoviePlanId(Long movieplanId);
}

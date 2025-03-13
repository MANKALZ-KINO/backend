package com.example.backend.repositories;

import com.example.backend.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findByTheaterId(Long theaterId);
}

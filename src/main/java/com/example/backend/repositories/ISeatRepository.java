package com.example.backend.repositories;

import com.example.backend.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ISeatRepository extends JpaRepository<Seat, Long> {

}

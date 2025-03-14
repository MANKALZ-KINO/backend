package com.example.backend.repositories;

import com.example.backend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Integer> {
}

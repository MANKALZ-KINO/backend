package com.example.backend.repositories;

import com.example.backend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findBySeat_SeatId(Long seatId);

    List<Ticket> findByPhoneNumber(int phoneNumber);

=======
public interface ITicketRepository extends JpaRepository<Ticket, Integer> {

}




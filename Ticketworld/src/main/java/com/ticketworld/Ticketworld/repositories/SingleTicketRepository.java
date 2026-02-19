package com.ticketworld.Ticketworld.repositories;

import com.ticketworld.Ticketworld.entity.SingleTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleTicketRepository extends JpaRepository<SingleTicket, Long> {
}

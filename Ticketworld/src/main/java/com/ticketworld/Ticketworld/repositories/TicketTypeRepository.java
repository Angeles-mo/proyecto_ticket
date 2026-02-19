package com.ticketworld.Ticketworld.repositories;

import com.ticketworld.Ticketworld.entity.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
}

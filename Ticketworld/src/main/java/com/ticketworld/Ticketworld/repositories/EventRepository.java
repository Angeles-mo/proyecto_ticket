package com.ticketworld.Ticketworld.repositories;

import com.ticketworld.Ticketworld.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}

package com.ticketworld.Ticketworld.services;

import com.ticketworld.Ticketworld.dto.EventDTO;
import com.ticketworld.Ticketworld.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EventService {

    ResponseEntity<?> createEvent(Account loggedAccount, EventDTO eventDTO);
    ResponseEntity<?> getAll();
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> deleteEvent(Account loggedAccount, Long id);
    ResponseEntity<?> putEvent(Account loggedAccount, Long id, EventDTO eventDTO);
}

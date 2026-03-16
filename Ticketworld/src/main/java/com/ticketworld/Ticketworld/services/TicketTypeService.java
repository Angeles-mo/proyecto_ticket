package com.ticketworld.Ticketworld.services;

import com.ticketworld.Ticketworld.dto.TicketTypeDTO;
import com.ticketworld.Ticketworld.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TicketTypeService {

    ResponseEntity<?> getAll();
    ResponseEntity<?> createTicketType(Account loggedAccount, TicketTypeDTO ticketTypeDTO);
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> deleteTicketType(Account loggedAccount, Long id);
    ResponseEntity<?> putTicketType(Account loggedAccount, Long id, TicketTypeDTO ticketTypeDTO);
}

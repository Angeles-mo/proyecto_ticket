package com.ticketworld.Ticketworld.services;

import com.ticketworld.Ticketworld.dto.SingleTicketDTO;
import com.ticketworld.Ticketworld.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface SingleTicketService {

    ResponseEntity<?> getAll();
    ResponseEntity<?> createSingleTicket(Account loggedAccount, SingleTicketDTO singleTicketDTO);
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> deleteSingleTicket(Account loggedAccount, Long id);
    ResponseEntity<?> putSingleTicket(Account loggedAccount, Long id, SingleTicketDTO singleTicketDTO);

}

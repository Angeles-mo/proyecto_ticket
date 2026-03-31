package com.ticketworld.Ticketworld.services;

import com.ticketworld.Ticketworld.dto.OrderDTO;
import com.ticketworld.Ticketworld.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    ResponseEntity<?> getAll(Account loggedAccount);
    ResponseEntity<?> createOrder(Account loggedAccount, OrderDTO orderDTO);
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> deleteOrder(Account loggedAccount, Long id);
    ResponseEntity<?> putOrder(Account loggedAccount, Long id, OrderDTO orderDTO);

}

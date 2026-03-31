package com.ticketworld.Ticketworld.services;

import com.ticketworld.Ticketworld.dto.PaymentDTO;
import com.ticketworld.Ticketworld.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    ResponseEntity<?> getAll(Account loggedAccount);
    ResponseEntity<?> createPayment(Account loggedAccount, PaymentDTO paymentDTO);
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> deletePayment(Account loggedAccount, Long id);
    ResponseEntity<?> putPayment(Account loggedAccount, Long id, PaymentDTO paymentDTO);

}

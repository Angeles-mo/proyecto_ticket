package com.ticketworld.Ticketworld.services;

import com.ticketworld.Ticketworld.dto.UserDTO;
import com.ticketworld.Ticketworld.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ResponseEntity<?> getAll(Account loggedAccount);
    ResponseEntity<?> getById(Account loggedAccount, Long id);
    ResponseEntity<?> deleteUser(Account loggedAccount, Long id);
    ResponseEntity<?> createUser(Account loggedAccount, UserDTO userDTO);
    ResponseEntity<?> putUser(Account loggedAccount, Long id, UserDTO userDTO);
}

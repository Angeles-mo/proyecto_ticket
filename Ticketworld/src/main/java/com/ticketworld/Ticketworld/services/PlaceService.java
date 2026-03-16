package com.ticketworld.Ticketworld.services;

import com.ticketworld.Ticketworld.dto.PlaceDTO;
import com.ticketworld.Ticketworld.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PlaceService {

    ResponseEntity<?> getAll();
    ResponseEntity<?> createPlace(Account loggedAccount, PlaceDTO placeDTO);
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> deletePlace(Account loggedAccount ,Long id);
    ResponseEntity<?> putPlace(Account loggedAccount ,Long id, PlaceDTO placeDTO);
}

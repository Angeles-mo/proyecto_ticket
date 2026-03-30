package com.ticketworld.Ticketworld.services;

import com.ticketworld.Ticketworld.dto.ArtistDTO;
import com.ticketworld.Ticketworld.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ArtistService {

    ResponseEntity<?> getAll();
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> deleteArtist(Account loggedAccount, Long id);
    ResponseEntity<?> createArtist(Account loggedAccount, ArtistDTO artistDTO);
    ResponseEntity<?> putArtist(Account loggedAccount, Long id, ArtistDTO artistDTO);
}

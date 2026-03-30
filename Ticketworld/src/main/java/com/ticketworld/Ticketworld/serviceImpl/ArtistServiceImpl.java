package com.ticketworld.Ticketworld.serviceImpl;

import com.ticketworld.Ticketworld.dto.ArtistDTO;
import com.ticketworld.Ticketworld.dto.ErrorDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.entity.Artist;
import com.ticketworld.Ticketworld.repositories.AccountRepository;
import com.ticketworld.Ticketworld.repositories.ArtistRepository;
import com.ticketworld.Ticketworld.services.ArtistService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ResponseEntity<?> getAll() {
        try {
            List<Artist> artists = artistRepository.findAll();
            return ResponseEntity.ok(artists);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorDTO("Error retrieving artists" + e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isPresent()){
            return ResponseEntity.ok(artist.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Artist not found");
        }
    }

    @Override
    public ResponseEntity<?> deleteArtist(Account loggedAccount, Long id) {
        if (!artistRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Artist not found"));
        }
        if (!loggedAccount.getId().equals(id) && !"ADMIN".equals(loggedAccount.getRole().name())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDTO("You don't have permission to delete this artist"));
        }
        artistRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @Transactional
    public ResponseEntity<?> createArtist(Account loggedAccount, ArtistDTO artistDTO) {
        try {

            //Verificamos si la cuenta ya tiene un artista (para evitar duplicados)
            if (loggedAccount.getArtist() != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorDTO("This account already has an artist profile"));
            }

            //Creamos al artista
            Artist artist = new Artist();
            artist.setName(artistDTO.getName());
            artist.setLastName(artistDTO.getLastName());
            artist.setMusicGenre(artistDTO.getMusicGenre());
            artist.setBiography(artistDTO.getBiography());
            artist.setEvents(artistDTO.getEvents());
            artist.setAccount(artistDTO.getAccount());

            //Aquí relacionamos la cuenta y el artista
            artist.setAccount(loggedAccount);

            //Guardamos al artista
            Artist savedArtist = artistRepository.save(artist);

            //Retornamos un DTO de respuesta
            return ResponseEntity.status(HttpStatus.CREATED).body(Artist.toDTO(savedArtist));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorDTO("Error creating the artist: " + e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<?> putArtist(Account loggedAccount, Long id, ArtistDTO artistDTO) {
        //Buscamos si el artista existe
        Optional<Artist> existingArtistOptional = artistRepository.findById(id);

        if (existingArtistOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Artist not found"));
        }
        if (!loggedAccount.getId().equals(id) && !"ADMIN".equals(loggedAccount.getRole().name())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDTO("You don't have permission to modify"));
        }

        Artist existingArtist = existingArtistOptional.get();

        existingArtist.setName(artistDTO.getName());
        existingArtist.setLastName(artistDTO.getLastName());
        existingArtist.setMusicGenre(artistDTO.getMusicGenre());
        existingArtist.setBiography(artistDTO.getBiography());
        existingArtist.setEvents(artistDTO.getEvents());
        existingArtist.setEvents(artistDTO.getEvents());

        Artist updateArtist = artistRepository.save(existingArtist);
        return ResponseEntity.ok(Artist.toDTO(updateArtist));
    }
}

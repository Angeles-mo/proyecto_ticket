package com.ticketworld.Ticketworld.controller;

import com.ticketworld.Ticketworld.dto.ArtistDTO;
import com.ticketworld.Ticketworld.dto.ArtistRequestDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.services.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artists")
@Tag(name = "Artists", description = "Artists management")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping
    @Operation(summary = "Get all artists")
    public ResponseEntity<?> getAllArtists(){
        return artistService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the artist by ID")
    public ResponseEntity<?> getArtistById(@PathVariable Long id){
        return artistService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete the artist")
    public ResponseEntity<?> deleteArtist(@AuthenticationPrincipal Account account, @PathVariable Long id){
        if (account == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authentify");
        }
        return artistService.deleteArtist(account, id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update/modify the artist")
    public ResponseEntity<?> putArtist(@AuthenticationPrincipal Account account,
                                       @PathVariable Long id,
                                       @RequestBody ArtistDTO artistDTO){
        return artistService.putArtist(account, id, artistDTO);
    }

    @PostMapping
    @Operation(summary = "Create the artist",
            description = "Create an artist and their associated account. Send 'artist' and 'account' in the same JSON.")
    public ResponseEntity<?> createArtist(@AuthenticationPrincipal Account account,
                                          @RequestBody ArtistRequestDTO artistRequestDTO){
        return artistService.createArtist(account, artistRequestDTO.getArtist(), artistRequestDTO.getAccount());
    }

}

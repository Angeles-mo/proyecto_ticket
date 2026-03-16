package com.ticketworld.Ticketworld.controller;

import com.ticketworld.Ticketworld.dto.PlaceDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.services.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/places")
@Tag(name = "Places", description = "Places management")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping()
    @Operation(summary = "Get all the places")
    public ResponseEntity<?> getAllPlaces(){
        return placeService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the place by ID")
    public ResponseEntity<?> getPlaceById(@PathVariable Long id){
        return placeService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete the place")
    public ResponseEntity<?> deletePlace(@AuthenticationPrincipal Account account, @PathVariable Long id){
        if (account == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authentify");
        }
        return placeService.deletePlace(account, id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update/modify the place")
    public ResponseEntity<?> putPlace(@AuthenticationPrincipal Account account,
                                      @PathVariable Long id,
                                      @RequestBody PlaceDTO placeDTO){
        return placeService.putPlace(account, id, placeDTO);
    }

    @PostMapping
    @Operation(summary = "Create the place")
    public ResponseEntity<?> createPlace(@AuthenticationPrincipal Account account, @RequestBody PlaceDTO placeDTO){
        return placeService.createPlace(account, placeDTO);
    }
}

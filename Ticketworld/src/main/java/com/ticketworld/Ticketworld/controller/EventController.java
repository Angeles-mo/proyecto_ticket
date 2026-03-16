package com.ticketworld.Ticketworld.controller;

import com.ticketworld.Ticketworld.dto.EventDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Events", description = "Events management")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping()
    @Operation(summary = "Get all the events")
    public ResponseEntity<?> getAllEvents(){
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the event by ID")
    public ResponseEntity<?> getEventById(@PathVariable Long id){
        return eventService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete the event")
    public ResponseEntity<?> deleteEvent(@AuthenticationPrincipal Account account, @PathVariable Long id){
        if (account == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authentify");
        }
        return eventService.deleteEvent(account, id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update/modify the event")
    public ResponseEntity<?> putEvent(@AuthenticationPrincipal Account account,
                                      @PathVariable Long id,
                                      @RequestBody EventDTO eventDTO){
        return eventService.putEvent(account, id, eventDTO);
    }

    @PostMapping
    @Operation(summary = "Create the event")
    public ResponseEntity<?> createEvent(@AuthenticationPrincipal Account account, @RequestBody EventDTO eventDTO){
        return eventService.createEvent(account, eventDTO);
    }
}

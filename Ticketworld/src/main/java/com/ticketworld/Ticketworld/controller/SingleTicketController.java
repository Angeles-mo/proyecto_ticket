package com.ticketworld.Ticketworld.controller;

import com.ticketworld.Ticketworld.dto.SingleTicketDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.entity.SingleTicket;
import com.ticketworld.Ticketworld.services.SingleTicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/singleTickets")
@Tag(name = "SingleTickets", description = "SingleTickets management")
public class SingleTicketController {

    @Autowired
    private SingleTicketService singleTicketService;

    @GetMapping()
    @Operation(summary = "Get all the singleTickets")
    public ResponseEntity<?> getAllSingleTickets(){
        return singleTicketService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the singleTicket by ID")
    public ResponseEntity<?> getSingleTicketById(@PathVariable Long id){
        return singleTicketService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete the singleTicket")
    public ResponseEntity<?> deleteSingleTicket(@AuthenticationPrincipal Account account, @PathVariable Long id){
        if (account == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authentify");
        }
        return singleTicketService.deleteSingleTicket(account, id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update/modify the singleTicket")
    public ResponseEntity<?> putSingleTicket(@AuthenticationPrincipal Account account,
                                             @PathVariable Long id,
                                             @RequestBody SingleTicketDTO singleTicketDTO){
        return singleTicketService.putSingleTicket(account, id, singleTicketDTO);
    }

    @PostMapping
    @Operation(summary = "Create the singleTicket")
    public ResponseEntity<?> createSingleTicket(@AuthenticationPrincipal Account account,
                                                @RequestBody SingleTicketDTO singleTicketDTO){
        return singleTicketService.createSingleTicket(account, singleTicketDTO);
    }
}

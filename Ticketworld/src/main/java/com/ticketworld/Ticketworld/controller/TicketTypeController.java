package com.ticketworld.Ticketworld.controller;

import com.ticketworld.Ticketworld.dto.TicketTypeDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.services.TicketTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticketTypes")
@Tag(name = "TicketTypes", description = "TicketTypes management")
public class TicketTypeController {

    @Autowired
    private TicketTypeService ticketTypeService;

    @GetMapping()
    @Operation(summary = "Get all the ticketTypes")
    public ResponseEntity<?> getAllTicketType(){
        return ticketTypeService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the ticketType by ID")
    public ResponseEntity<?> getTicketTypeById(@PathVariable Long id){
        return ticketTypeService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete the ticketType")
    public ResponseEntity<?> deleteTicketType(@AuthenticationPrincipal Account account, @PathVariable Long id){
        if (account == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authentify");
        }
        return ticketTypeService.deleteTicketType(account, id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update/modify the ticketType")
    public ResponseEntity<?> putTicketType(@AuthenticationPrincipal Account account,
                                           @PathVariable Long id,
                                           @RequestBody TicketTypeDTO ticketTypeDTO){
        return ticketTypeService.putTicketType(account, id, ticketTypeDTO);
    }

    @PostMapping
    @Operation(summary = "Create the ticketType")
    public ResponseEntity<?> createTicketType(@AuthenticationPrincipal Account account,
                                              @RequestBody TicketTypeDTO ticketTypeDTO){
        return ticketTypeService.createTicketType(account, ticketTypeDTO);
    }
}

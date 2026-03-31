package com.ticketworld.Ticketworld.controller;

import com.ticketworld.Ticketworld.dto.UserDTO;
import com.ticketworld.Ticketworld.dto.UserRequestDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.services.AccountService;
import com.ticketworld.Ticketworld.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Users management")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping()
    @Operation(summary = "Get all the users")
    public ResponseEntity<?> gettAllUsers(@AuthenticationPrincipal Account account){
        return userService.getAll(account);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the user by ID")
    public ResponseEntity<?> getUserByiD(@AuthenticationPrincipal Account account, @PathVariable Long id){
        return userService.getById(account, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete the user")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal Account account, @PathVariable Long id){
        if (account == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authentify");
        }
        return userService.deleteUser(account, id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update/modify the user")
    public ResponseEntity<?> putUser(@AuthenticationPrincipal Account account,
                                     @PathVariable Long id,
                                     @RequestBody UserDTO userDTO){
        return userService.putUser(account, id, userDTO);
    }

    @PostMapping
    @Operation(summary = "Create the user",
            description = "Create a user and their associated account. Send 'user' and 'account' in the same JSON.")
    public ResponseEntity<?> createUser(@AuthenticationPrincipal UserDetails userDetails,
                                        @RequestBody UserRequestDTO userRequestDTO){
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        Account account = accountService.findByEmail(userDetails.getUsername());
        return userService.createUser(account, userRequestDTO.getUser());
    }
}

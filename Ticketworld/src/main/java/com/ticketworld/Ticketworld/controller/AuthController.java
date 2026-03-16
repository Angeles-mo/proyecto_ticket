package com.ticketworld.Ticketworld.controller;

import com.ticketworld.Ticketworld.dto.AccountDTO;
import com.ticketworld.Ticketworld.dto.LoginRequest;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.jwt.JwtService;
import com.ticketworld.Ticketworld.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// controller/AuthController.java
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auths", description = "Auths management")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired private JwtService jwtService;
    @Autowired private AccountService accountService;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @Operation(summary = "Log in", description = "Returns a JWT token")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Account account = accountService.findByEmail(request.getEmail());
        String token = jwtService.generateToken(account);

        return ResponseEntity.ok(Map.of("token", token, "role", account.getRole()));
    }

    @PostMapping("/register")
    @Operation(summary = "Register account", description = "Create a new account")
    public ResponseEntity<?> register(@RequestBody AccountDTO accountDTO) {
        accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        accountService.save(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
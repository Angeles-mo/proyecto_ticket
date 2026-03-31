package com.ticketworld.Ticketworld.controller;

import com.ticketworld.Ticketworld.dto.PaymentDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payments", description = "Payments management")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    @Operation(summary = "Get all the payments")
    public ResponseEntity<?> getAllPayments(@AuthenticationPrincipal Account account) {
        return paymentService.getAll(account);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get the payment by ID")
    public ResponseEntity<?> getPaymentById(@PathVariable Long id){
        return paymentService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete the payment")
    public ResponseEntity<?> deletePayment(@AuthenticationPrincipal Account account, @PathVariable Long id){
        if (account == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authentify");
        }
        return paymentService.deletePayment(account, id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update/modify the payment")
    public ResponseEntity<?> putPayment(@AuthenticationPrincipal Account account,
                                        @PathVariable Long id,
                                        @RequestBody PaymentDTO paymentDTO){
        return paymentService.putPayment(account, id, paymentDTO);
    }

    @PostMapping
    @Operation(summary = "Create the payment")
    public ResponseEntity<?> createPayment(@AuthenticationPrincipal Account account,
                                           @RequestBody PaymentDTO paymentDTO){
        return paymentService.createPayment(account, paymentDTO);
    }
}

package com.ticketworld.Ticketworld.controller;

import com.ticketworld.Ticketworld.dto.OrderDTO;
import com.ticketworld.Ticketworld.entity.Account;
import com.ticketworld.Ticketworld.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders", description = "Order management")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @Operation(summary = "Get all orders")
    public ResponseEntity<?> getAllOrders(){
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the order by ID")
    public  ResponseEntity<?> getOrderById(@PathVariable Long id){
     return orderService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete the order")
    public ResponseEntity<?> deleteOrder(@AuthenticationPrincipal Account account, @PathVariable Long id){
        if (account == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authentify");
        }
        return orderService.deleteOrder(account, id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update/modify the order")
    public ResponseEntity<?> putOrder(@AuthenticationPrincipal Account account,
                                      @PathVariable Long id,
                                      @RequestBody OrderDTO orderDTO){
        return orderService.putOrder(account, id, orderDTO);
    }

    @PostMapping
    @Operation(summary = "Create the order")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal Account account, @RequestBody OrderDTO orderDTO){
        return orderService.createOrder(account, orderDTO);
    }
}

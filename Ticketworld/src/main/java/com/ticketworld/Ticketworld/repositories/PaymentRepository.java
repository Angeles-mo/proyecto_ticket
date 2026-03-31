package com.ticketworld.Ticketworld.repositories;

import com.ticketworld.Ticketworld.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrderUserId(Long userId);
}

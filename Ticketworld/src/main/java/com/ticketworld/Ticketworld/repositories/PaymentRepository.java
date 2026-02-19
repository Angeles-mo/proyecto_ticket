package com.ticketworld.Ticketworld.repositories;

import com.ticketworld.Ticketworld.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

package com.ticketworld.Ticketworld.repositories;

import com.ticketworld.Ticketworld.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

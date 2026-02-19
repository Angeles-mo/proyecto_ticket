package com.ticketworld.Ticketworld.repositories;

import com.ticketworld.Ticketworld.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

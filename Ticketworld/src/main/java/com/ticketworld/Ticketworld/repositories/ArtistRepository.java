package com.ticketworld.Ticketworld.repositories;

import com.ticketworld.Ticketworld.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
}

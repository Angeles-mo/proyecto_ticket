package com.ticketworld.Ticketworld.repositories;

import com.ticketworld.Ticketworld.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}

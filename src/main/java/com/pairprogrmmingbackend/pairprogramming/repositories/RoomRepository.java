package com.pairprogrmmingbackend.pairprogramming.repositories;

import com.pairprogrmmingbackend.pairprogramming.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}

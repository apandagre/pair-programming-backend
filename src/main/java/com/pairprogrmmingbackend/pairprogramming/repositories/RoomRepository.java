package com.pairprogrmmingbackend.pairprogramming.repositories;

import com.pairprogrmmingbackend.pairprogramming.dtos.RoomDto;
import com.pairprogrmmingbackend.pairprogramming.entities.Room;
import com.pairprogrmmingbackend.pairprogramming.entities.User;
import com.pairprogrmmingbackend.pairprogramming.exceptions.AppException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByName(String name);
    Optional<Room> findByName(String name);
    List<Room> findByOwner(User currentUser);

    public default void updateCode(String roomName, String code) {
        System.out.println("[roomRepository] updateCode");
        Optional<Room> roomOptional = this.findByName(roomName);
        if(roomOptional.isPresent()) {
            Room room = roomOptional.get();
            System.out.println("setting code " + code);
            room.setCode(code);
            this.save(room);
        } else {
            throw new AppException("Room does not exist.", HttpStatus.NOT_FOUND);
        }

    }

    public default void addMember(String roomName) {
        Optional<Room> roomOptional = this.findByName(roomName);
        if(roomOptional.isPresent()) {
            Room room = roomOptional.get();
            System.out.println("adding member ");
            room.setMembers(room.getMembers()+1);
            this.save(room);
        } else {
            throw new AppException("Room does not exist.", HttpStatus.NOT_FOUND);
        }
    }

    public default void removeMember(String roomName) {
        Optional<Room> roomOptional = this.findByName(roomName);
        if(roomOptional.isPresent()) {
            Room room = roomOptional.get();
            System.out.println("removing member ");
            room.setMembers(room.getMembers()-1);
            this.save(room);
        } else {
            throw new AppException("Room does not exist.", HttpStatus.NOT_FOUND);
        }
    }

}

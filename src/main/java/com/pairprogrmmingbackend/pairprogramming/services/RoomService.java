package com.pairprogrmmingbackend.pairprogramming.services;

import com.pairprogrmmingbackend.pairprogramming.entities.Room;
import com.pairprogrmmingbackend.pairprogramming.entities.User;
import com.pairprogrmmingbackend.pairprogramming.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public Room createRoom(String name, String language, User owner) {
        System.out.println("[roomService] creating a room");
        Room room = new Room();
        room.setName(name);
        room.setLanguage(language);
        room.setOwner(owner);
        room.setCode("");
        return roomRepository.save(room);
    }
}

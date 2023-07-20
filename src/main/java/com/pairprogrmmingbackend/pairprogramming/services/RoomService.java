package com.pairprogrmmingbackend.pairprogramming.services;

import com.pairprogrmmingbackend.pairprogramming.entities.Room;
import com.pairprogrmmingbackend.pairprogramming.entities.User;
import com.pairprogrmmingbackend.pairprogramming.exceptions.AppException;
import com.pairprogrmmingbackend.pairprogramming.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public boolean isRoomNameExists(String name) {
        return roomRepository.existsByName(name);
    }

    public Room createRoom(String name, String language, User owner) {
        System.out.println("[roomService] creating a room");
        if (isRoomNameExists(name)) {
            throw new AppException("Room name already exists", HttpStatus.BAD_REQUEST);
        }

        Room room = new Room();
        room.setName(name);
        room.setLanguage(language);
        room.setOwner(owner);
        room.setCode("");
        return roomRepository.save(room);
    }

    public Optional<Room> getRoom(String name) {
        return roomRepository.findByName(name);
    }
}

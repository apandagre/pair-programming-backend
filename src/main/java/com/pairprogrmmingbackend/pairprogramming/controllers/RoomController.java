package com.pairprogrmmingbackend.pairprogramming.controllers;

import com.pairprogrmmingbackend.pairprogramming.dtos.RoomCreationRequest;
import com.pairprogrmmingbackend.pairprogramming.dtos.RoomDto;
import com.pairprogrmmingbackend.pairprogramming.dtos.UserDto;
import com.pairprogrmmingbackend.pairprogramming.entities.Room;
import com.pairprogrmmingbackend.pairprogramming.entities.User;
import com.pairprogrmmingbackend.pairprogramming.exceptions.AppException;
import com.pairprogrmmingbackend.pairprogramming.mappers.RoomMapper;
import com.pairprogrmmingbackend.pairprogramming.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final RoomMapper roomMapper;

//    works, but the owner is null..
//    @PostMapping
//    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomCreationRequest roomCreationRequest, @AuthenticationPrincipal User currentUser) {
//        System.out.println("[roomController] creating a room with user " + currentUser.toString());
//        Room room = roomService.createRoom(roomCreationRequest.getName(), roomCreationRequest.getLanguage(), currentUser);
//        return ResponseEntity.ok(roomMapper.toRoomDto(room));
//    }

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomCreationRequest roomCreationRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("[roomController] creating a new room with user ");
        Room room = roomService.createRoom(roomCreationRequest.getName(), roomCreationRequest.getLanguage(), (User) authentication.getPrincipal());
        return ResponseEntity.ok(roomMapper.toRoomDto(room));
    }

    @GetMapping("/{roomName}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable String roomName) {
        System.out.println("[getRoom] " + roomName);
        Optional<Room> room = roomService.getRoom(roomName);
        if(room.isEmpty()) throw  new AppException("Room does not exist", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(roomMapper.toRoomDto(room.get()));
    }
}

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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomCreationRequest roomCreationRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("[roomController] creating a new room with user ");
        Room room = roomService.createRoom(roomCreationRequest.getName(), roomCreationRequest.getLanguage(), (User) authentication.getPrincipal());
        return ResponseEntity.ok(roomMapper.toRoomDto(room));
    }

    @PatchMapping("/save/{roomName}")
    public ResponseEntity<String> saveCode(@PathVariable String roomName, @RequestBody String code) {
        System.out.println("received info " + roomName + " " + code);
        roomService.updateCode(roomName, code);
        return ResponseEntity.ok("OK");
    }

    @GetMapping
    public ResponseEntity<List<Room>> getUserRooms() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("getting a user's created rooms..");
        User currentUser = (User) authentication.getPrincipal();
        List<Room> rooms = roomService.getUserRooms(currentUser);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{roomName}")
    public ResponseEntity<Room> getRoom(@PathVariable String roomName) {
        System.out.println("[getRoom] " + roomName);
        Optional<Room> room = roomService.getRoom(roomName);
        if(room.isEmpty()) throw  new AppException("Room does not exist", HttpStatus.NOT_FOUND);
        System.out.println("[roomController] " + room.get());
        return ResponseEntity.ok(room.get());
    }

    @PostMapping("/add-member/{roomName}")
    public void addMember(@PathVariable String roomName) {
        System.out.println("adding member");
        roomService.addMember(roomName);
    }

    @PostMapping("/remove-member/{roomName}")
    public void removeMember(@PathVariable String roomName) {
        roomService.removeMember(roomName);
    }
}

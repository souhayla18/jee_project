package com.ensah.core.web.controllers;


import com.ensah.core.bo.Room;
import com.ensah.core.services.IRoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Room")
public class RoomController {

    @Autowired
    IRoomService roomService;

    @PostMapping()
    public ResponseEntity<Room> addRoomRS(@RequestBody Room Room) {
        roomService.addRoom(Room);
        return ResponseEntity.noContent().build();
    }



    @PutMapping("/{RoomnelId}")
    public ResponseEntity<Room> updateRoomRS(@PathVariable Long RoomnelId,@Valid @RequestBody Room RoomnelDetails) {
        roomService.updateRoom(RoomnelId, RoomnelDetails);
        return ResponseEntity.noContent().build();
    }



    @GetMapping
    public ResponseEntity<List<Room>> getAllRoomRS() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }



    @GetMapping("/{RoomnelId}")
    public ResponseEntity<Room> getOneRoomRS(@PathVariable Long RoomnelId) {
        return ResponseEntity.ok(roomService.getRoomById(RoomnelId));

    }

    @DeleteMapping("/{RoomnelId}")
    public ResponseEntity<Void> deleteRoomRS(@PathVariable Long RoomnelId) {
        roomService.deleteRoom(RoomnelId);
        return ResponseEntity.noContent().build();
    }

}



package com.ensah.core.services.Impl;

import com.ensah.core.bo.Room;
import com.ensah.core.dao.IRoomDao;
import com.ensah.core.services.IRoomService;
import com.ensah.core.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class RoomServiceImpl implements IRoomService {

    @Autowired
    IRoomDao roomDao;

    public void addRoom(Room pRoom) {
        roomDao.save(pRoom);

    }

    public void updateRoom(Long roomId, Room pRoom) {
        Optional<Room> roomOptional = roomDao.findById(roomId);
        if (roomOptional.isEmpty()) {
            throw new ResourceNotFoundException("Room", "id", roomId);
        }
        Room room = roomOptional.get();

        if (pRoom.getNameRoom() != null) {
            room.setNameRoom(pRoom.getNameRoom());
        }

        if (pRoom.getSize() != null) {
            room.setSize(pRoom.getSize());
        }

        if (pRoom.getType() != null) {
            room.setType(pRoom.getType());
        }

        if (pRoom.getPlace() != null) {
            room.setPlace(pRoom.getPlace());
        }

        roomDao.save(room);
    }

    public List<Room> getAllRooms() {
        return roomDao.findAll();

    }

    public void deleteRoom(Long id) {
        if (getRoomById(id) != null){
            roomDao.deleteById(id);
        }

    }

    public Room getRoomById(Long id) {
        return roomDao.findById(id).get();

    }

    public Room getRoomByNameRoom(String NameRoom) {
        return roomDao.getRoomByNameRoom(NameRoom);
    }

    public void assignMonitorsToRooms(Set<Room> rooms, int nbrMonitors) {
        for (Room room : rooms) {

            roomDao.save(room);
        }
    }

}

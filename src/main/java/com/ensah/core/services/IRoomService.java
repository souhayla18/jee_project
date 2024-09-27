package com.ensah.core.services;

import com.ensah.core.bo.Room;

import java.util.List;
import java.util.Set;


public interface IRoomService {

    public void addRoom(Room pRoom);

    public void updateRoom(Long roomId,Room pRoom);

    public List<Room> getAllRooms();

    public void deleteRoom(Long id);

    public Room getRoomById(Long id);

    public Room getRoomByNameRoom(String nameRoom);

     public void assignMonitorsToRooms(Set<Room> rooms, int defaultInvigilators)

}

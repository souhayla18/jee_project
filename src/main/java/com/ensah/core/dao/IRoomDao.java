package com.ensah.core.dao;

import com.ensah.core.bo.Person;
import com.ensah.core.bo.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRoomDao extends JpaRepository<Room, Long> {
    Room getRoomByNameRoom(String nameRoom);


}

package com.epam.training.ticketservice.core.room;

import com.epam.training.ticketservice.core.room.model.RoomDto;

public interface RoomService {
    void createRoom(RoomDto room);

    String updateRoom(RoomDto room);

    void deleteRoom(String roomName);

    String getRoomList();
}

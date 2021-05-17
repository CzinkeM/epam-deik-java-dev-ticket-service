package com.epam.training.ticketservice.core.room.impl;

import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistance.entity.Room;
import com.epam.training.ticketservice.core.room.persistance.repository.RoomRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @PostConstruct
    public void init() {
        Room room1 = new Room(null, "Room 1", 6, 20);
        Room room2 = new Room(null, "Room 2", 10, 24);
        roomRepository.saveAll(List.of(room1, room2));
    }

    @Override
    public void createRoom(RoomDto roomDto) {
        Objects.requireNonNull(roomDto, "Room cannot be null");
        Objects.requireNonNull(roomDto.getName(), "Room's name cannot be null");
        Objects.requireNonNull(roomDto.getSeatRows(), "Room's rows cannot be null");
        Objects.requireNonNull(roomDto.getSeatColumns(), "Room's columns cannot be null");
        Room room = new Room(null, roomDto.getName(), roomDto.getSeatRows(), roomDto.getSeatColumns());
        roomRepository.save(room);
    }

    @Override
    public String updateRoom(RoomDto roomDto) {
        Objects.requireNonNull(roomDto, "Room cannot be null");
        if (roomDto.getSeatNumbers() <= 0) {
            return "Please provide a valid row and column number";
        }
        if (roomRepository.findByName(roomDto.getName()).isPresent()) {
            Optional<Room> room = roomRepository.findByName(roomDto.getName());
            room = Optional.of(new Room(
                    room.get().getId(),
                    roomDto.getName(),
                    roomDto.getSeatRows(),
                    roomDto.getSeatColumns()));
            roomRepository.save(room.get());
            return (room.get().getName() + " were successfully updated");
        } else {
            if (roomRepository.findByName(roomDto.getName()).isEmpty()) {
                return "Cannot update a non-existing element";
            } else {
                return "Something went wrong";
            }
        }
    }

    @Override
    public void deleteRoom(String roomName) {
        Room roomToDelete = roomRepository.findAll().stream().filter(r -> r.getName().equals(roomName))
                .findAny().orElse(null);
        roomRepository.delete(roomToDelete);
    }

    @Override
    public String getRoomList() {
        String returnMessage;
        if (roomRepository.findAll().isEmpty()) {
            returnMessage = "There are no rooms at the moment";
        } else {
            returnMessage = roomRepository.findAll().stream().map(this::convertEntityToDto)
                    .collect(Collectors.toList()).toString();
        }
        return returnMessage;
    }

    private RoomDto convertEntityToDto(Room room) {
        return new RoomDto(room.getName(), room.getRows(), room.getColumns());
    }
}

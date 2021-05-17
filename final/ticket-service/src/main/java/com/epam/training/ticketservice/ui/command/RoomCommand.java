package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.user.LoginService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class RoomCommand {

    private final RoomService roomService;
    private final LoginService loginService;

    public RoomCommand(RoomService roomService, LoginService loginService) {
        this.roomService = roomService;
        this.loginService = loginService;
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Create room - args: <name> <rows> <columns>", key = "create room")
    public RoomDto createRoom(String name, int rows, int columns) {
        RoomDto roomDto = new RoomDto(name, rows, columns);
        roomService.createRoom(roomDto);
        return null;
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Delete Room - arg: <name>", key = "delete room")
    public String deleteRoom(String name) {
        try {
            roomService.deleteRoom(name);
            return (name + " named room was deleted");
        } catch (IllegalArgumentException e) {
            return ("Something went wrong");
        }
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Update Roome - args: <name> <rows> <columns>", key = "update room")
    public String updateRoom(String name, int rows, int columns) {
        RoomDto roomDto = new RoomDto(name, rows, columns);
        return roomService.updateRoom(roomDto);
    }

    @ShellMethod(value = "List rooms - no argument", key = "list rooms")
    public String listRooms() {
        return roomService.getRoomList();
    }

    private Availability isAvailable() {
        return loginService.isAdminCommandAreAvailable();
    }
}

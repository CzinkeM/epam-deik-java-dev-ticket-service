package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.user.LoginService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.text.ParseException;

@ShellComponent
public class ScreeningCommand {

    private final ScreeningService screeningService;
    private final LoginService loginService;

    public ScreeningCommand(ScreeningService screeningService, LoginService loginService) {
        this.screeningService = screeningService;
        this.loginService = loginService;
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Create Screening", key = "create screening")
    public String createScreening(String movieName, String roomName, String screeningTime) throws ParseException {
        return screeningService.createScreening(movieName, roomName, screeningTime);
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Delete Screening", key = "delete screen")
    public String deleteScreening(String movieName) {
        return "";
    }

    @ShellMethod(value = "List Screenings", key = "list screenings")
    public String listScreening() {
        return screeningService.getScreeningsList();
    }

    private Availability isAvailable() {
        return loginService.isAdminCommandAreAvailable();
    }
}

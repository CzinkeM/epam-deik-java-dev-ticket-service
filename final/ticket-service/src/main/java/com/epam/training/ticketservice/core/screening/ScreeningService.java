package com.epam.training.ticketservice.core.screening;

import java.text.ParseException;

public interface ScreeningService {
    String getScreeningsList();

    String createScreening(String movieTitle, String roomName, String startingDate) throws ParseException;

    void deleteScreening(String screening);
}

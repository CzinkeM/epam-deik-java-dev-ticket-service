package com.epam.training.ticketservice.core.screening.model;

import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.room.persistance.entity.Room;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ScreeningDto {

    private Movie movie;
    private Room room;
    private Date screeningTimeStart;
    private Date screeningTimeEnd;

    public ScreeningDto(Movie movieTitle, Room roomName, String screeningTimeStart) throws ParseException {
        this.movie = movieTitle;
        this.room = roomName;
        this.screeningTimeStart = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(screeningTimeStart);
    }

    public ScreeningDto(Movie movie, Room room, Date screeningTimeStart) {
        this.movie = movie;
        this.room = room;
        this.screeningTimeStart = screeningTimeStart;
    }

    public Date getScreeningTimeEnd() {
        return DateUtils.addMinutes(screeningTimeStart, movie.getLength());
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getScreeningTimeStart() {
        return screeningTimeStart;
    }

    public void setScreeningTimeStart(Date screeningTimeStart) {
        this.screeningTimeStart = screeningTimeStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScreeningDto that = (ScreeningDto) o;
        return Objects.equals(movie, that.movie)
                && Objects.equals(room, that.room)
                && Objects.equals(screeningTimeStart, that.screeningTimeStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, room, screeningTimeStart);
    }

    @Override
    public String toString() {
        return "ScreeningDto{"
                + "movie="
                + movie
                + ", room="
                + room
                + ", startDate="
                + screeningTimeStart
                + '}';
    }
}

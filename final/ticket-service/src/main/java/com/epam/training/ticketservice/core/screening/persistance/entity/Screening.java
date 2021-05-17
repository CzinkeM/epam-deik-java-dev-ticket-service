package com.epam.training.ticketservice.core.screening.persistance.entity;

import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.room.persistance.entity.Room;
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Room room;
    private Date screeningTimeStart;
    private Date screeningTimeEnd;

    public Screening() {
    }

    public Screening(Integer id, Movie movie, Room room, Date screeningTimeStart) {
        this.id = id;
        this.movie = movie;
        this.room = room;
        this.screeningTimeStart = screeningTimeStart;
    }

    public Date getScreeningTimeEnd() {
        return DateUtils.addMinutes(screeningTimeStart, movie.getLength());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movieTitle) {
        this.movie = movieTitle;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room roomName) {
        this.room = roomName;
    }

    public Date getScreeningTimeStart() {
        return screeningTimeStart;
    }

    public void setScreeningTimeStart(Date screeningTime) {
        this.screeningTimeStart = screeningTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Screening screening = (Screening) o;
        return Objects.equals(id, screening.id) && Objects.equals(movie, screening.movie)
                && Objects.equals(room, screening.room)
                && Objects.equals(screeningTimeStart, screening.screeningTimeStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, room, screeningTimeStart);
    }

    @Override
    public String toString() {
        return "Screening{"
                + "id="
                + id
                + ", movieTitle="
                + movie
                + ", roomName="
                + room
                + ", screeningTime="
                + screeningTimeStart
                + '}';
    }
}

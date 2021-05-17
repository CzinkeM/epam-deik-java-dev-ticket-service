package com.epam.training.ticketservice.core.screening.impl;

import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistance.repository.MovieRepository;
import com.epam.training.ticketservice.core.room.persistance.entity.Room;
import com.epam.training.ticketservice.core.room.persistance.repository.RoomRepository;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.screening.persistance.entity.Screening;
import com.epam.training.ticketservice.core.screening.persistance.repository.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;

    public ScreeningServiceImpl(ScreeningRepository screeningRepository,
                                MovieRepository movieRepository,
                                RoomRepository roomRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public String getScreeningsList() {
        return screeningRepository.findAll().stream().map(this::convertEntityToDto)
                .collect(Collectors.toList()).toString();
    }

    @Override
    public String createScreening(String movieTitle, String roomName, String startingDate) throws ParseException {
        String returnMessage;
        Movie movie = movieRepository.findByTitle(movieTitle).get();
        Room room = roomRepository.findByName(roomName).get();
        ScreeningDto screeningDto = new ScreeningDto(movie, room, startingDate);
        Screening screening = new Screening(null,
                screeningDto.getMovie(),
                screeningDto.getRoom(),
                screeningDto.getScreeningTimeStart());
        if (!inValidStartingDate(screeningDto)) {
            screeningRepository.save(screening);
            returnMessage = "hurray";
        } else { // TODO: 2021. 05. 17. proper message and break branch
            returnMessage = "faszomba";
        }

        return returnMessage;
    }

    private boolean inValidStartingDate(ScreeningDto screeningDto) {
        boolean isInvalid = false;
        Room room = screeningDto.getRoom();
        List<Screening> roomScreenings = screeningRepository.findAll().stream().filter(s -> room.getName()
                .equals(screeningDto.getRoom().getName()))
                .sorted(Comparator.comparing(Screening::getScreeningTimeStart)).collect(Collectors.toList());
        switch (roomScreenings.size()) {
            case 0: {
                isInvalid = false;
                break;
            }
            case 1: {
                isInvalid = roomScreenings.get(0).getScreeningTimeStart().getTime()
                        <= screeningDto.getScreeningTimeEnd().getTime()
                        && roomScreenings.get(0).getScreeningTimeEnd().getTime()
                        >= screeningDto.getScreeningTimeStart().getTime();
                break;
            }
            default: {
                for (int i = 0; i < roomScreenings.size(); i++) {
                    isInvalid = roomScreenings.get(i).getScreeningTimeEnd().getTime()
                            >= screeningDto.getScreeningTimeStart().getTime()
                            || roomScreenings.get(i + 1).getScreeningTimeStart().getTime()
                            <= screeningDto.getScreeningTimeStart().getTime();
                }
                break;
            }
        }
        return isInvalid;
    }

    @Override
    public void deleteScreening(String screening) {
        // TODO: 2021. 05. 17. delete screen
    }

    private ScreeningDto convertEntityToDto(Screening screening) {
        return new ScreeningDto(screening.getMovie(), screening.getRoom(), screening.getScreeningTimeStart());
    }
}

package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.user.LoginService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;

@ShellComponent
public class MovieCommand {

    private final MovieService movieService;
    private final LoginService loginService;

    public MovieCommand(MovieService movieService, LoginService loginService) {
        this.movieService = movieService;
        this.loginService = loginService;
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Create Movie", key = "create movie")
    public String createMovie(String title, String genre, int length) {
        MovieDto movie = new MovieDto(title, genre, length);
        return movieService.createMovie(movie);
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Update Movie", key = "update movie")
    public String updateMovie(String title, String genre, int length) {
        MovieDto movie = new MovieDto(title, genre, length);
        return movieService.updateMovie(movie);
    }

    @ShellMethodAvailability("isAvailable")
    @ShellMethod(value = "Delete Movie", key = "delete movie")
    public String deleteMovie(String title) {
        try {
            movieService.deleteMovie(title);
            return (title + " named room was deleted");
        } catch (IllegalArgumentException e) {
            return ("Something went wrong");
        }
    }

    @ShellMethod(value = "List movies", key = "list movies")
    public List<MovieDto> listMovies() {
        return movieService.getMovieList();
    }

    private Availability isAvailable() {
        return loginService.isAdminCommandAreAvailable();
    }
}

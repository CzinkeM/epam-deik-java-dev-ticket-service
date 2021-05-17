package com.training.epam.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.impl.MovieServiceImpl;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.user.LoginService;
import com.epam.training.ticketservice.core.user.impl.LoginServiceImpl;
import com.epam.training.ticketservice.ui.command.MovieCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

public class MovieCommandTest {
    @Mock
    private MovieService movieService;
    @Mock
    private LoginService loginService;

    private MovieCommand underTest;

    @BeforeEach
    public void init() {
        movieService = Mockito.mock(MovieServiceImpl.class);
        loginService = Mockito.mock(LoginServiceImpl.class);
        underTest = new MovieCommand(movieService,loginService);
    }
    @Test
    public void testCreateMovieShouldReturnCorrectStringWhenMovieCreated(){
        MovieDto movieDto = new MovieDto("test","test",90);
        Movie movie = new Movie(null,"test","test",90);
        Mockito.when(movieService.createMovie(movieDto)).thenReturn(movie + " successfully created.");

        String actual = underTest.createMovie("test","test",90);
        String expected = movie + " successfully created.";

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void testCreateMovieShouldReturnCorrectStringWhenLengthIsToHigh(){
        MovieDto movieDto = new MovieDto("test","test",1300);
        Mockito.when(movieService.createMovie(movieDto)).thenReturn("Please provide a valid movie length");

        String actual = underTest.createMovie("test","test",1300);
        String expected = "Please provide a valid movie length";

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void testCreateMovieShouldReturnCorrectStringWhenLengthIsNegative(){
        MovieDto movieDto = new MovieDto("test","test",-10);
        Mockito.when(movieService.createMovie(movieDto)).thenReturn("Please provide a valid movie length");

        String actual = underTest.createMovie("test","test",-10);
        String expected = "Please provide a valid movie length";

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void testUpdateMovieShouldReturnCorrectStringWhenMovieUpdated(){
        MovieDto movieDto = new MovieDto("test","test",100);
        Mockito.when(movieService.updateMovie(movieDto)).thenReturn(movieDto.getTitle() + " were successfully updated");

        String actual = underTest.updateMovie("test","test",100);
        String expected = movieDto.getTitle() + " were successfully updated";

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void testUpdateMovieShouldReturnAppropriateStringWhenLengthIsInvalid(){
        MovieDto movieDto = new MovieDto("test","test",-10);
        Mockito.when(movieService.updateMovie(movieDto)).thenReturn("Please provide a valid movie length");

        String actual = underTest.updateMovie("test","test",-10);
        String expected = "Please provide a valid movie length";

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void testUpdateMovieShouldReturnAppropriateStringWhenMovieDoesntExist(){
        MovieDto movieDto = new MovieDto("test","test",100);
        Mockito.when(movieService.updateMovie(movieDto)).thenReturn("Cannot update a non-existing element");

        String actual = underTest.updateMovie("test","test",100);
        String expected = "Cannot update a non-existing element";

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void testDeleteMovieShouldReturnAppropriateStringWhenMovieSuccessfullyDeleted(){
        MovieDto movieDto = new MovieDto("test","test",100);

        String actual = underTest.deleteMovie("test");
        String expected = movieDto.getTitle() + " named room was deleted";

        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void testListMoviesShouldReturnListOfMovieDto(){
        MovieDto movieDto = new MovieDto("test","test",100);
        MovieDto movie2Dto = new MovieDto("test2","test2",200);
        Mockito.when(movieService.getMovieList()).thenReturn(List.of(movieDto,movie2Dto));

        List<MovieDto> actual = underTest.listMovies();
        List<MovieDto> expected = List.of(movieDto,movie2Dto);

        Assertions.assertEquals(expected,actual);

    }

}

package com.training.epam.ticketservice.core.movie;

import com.epam.training.ticketservice.core.movie.impl.MovieServiceImpl;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistance.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MovieServiceImplTest {
    @Mock
    private MovieRepository movieRepository;

    private MovieServiceImpl underTest;

    @BeforeEach
    public void init() {
        movieRepository = Mockito.mock(MovieRepository.class);
        underTest = new MovieServiceImpl(movieRepository);
    }

    @Test
    void testCreateMovieShouldCreateMovieWhenDoesntExist() {
        // Given
        Movie movie = new Movie(null,"test", "test", 90);

        // When
        underTest.createMovie(new MovieDto("test", "test", 90));

        // Then
        verify(movieRepository, times(1)).save(movie);
    }
    @Test
    public void testConvertEntityToDto(){
        Movie movie = new Movie(null,"test","test",90);
        MovieDto expected = new MovieDto("test","test",90);
        MovieDto actual = underTest.convertEntityToDto(movie);

        Assertions.assertEquals(expected,actual);
    }
}

package com.epam.training.ticketservice.core.movie;

import com.epam.training.ticketservice.core.movie.model.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getMovieList();

    void deleteMovie(String movieTitle);

    String createMovie(MovieDto movie);

    String updateMovie(MovieDto movieDto);

}

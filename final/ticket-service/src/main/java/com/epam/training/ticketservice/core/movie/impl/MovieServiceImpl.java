package com.epam.training.ticketservice.core.movie.impl;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistance.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistance.repository.MovieRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void init() {
        Movie starwars = new Movie(null, "Star Wars", "sci-fi", 120);
        Movie dinoteso = new Movie(null, "Dínó tesó", "animation", 100);
        movieRepository.saveAll(List.of(starwars, dinoteso));
    }

    @Override
    public List<MovieDto> getMovieList() {
        return movieRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteMovie(String movieTitle) {
        Movie movieToDelete = movieRepository.findAll().stream().filter(r -> r.getTitle().equals(movieTitle))
                .findAny().orElse(null);
        movieRepository.delete(movieToDelete);
    }

    @Override
    public String createMovie(MovieDto movieDto) {
        Objects.requireNonNull(movieDto, "Movie cannot be null");
        Objects.requireNonNull(movieDto.getTitle(), "Movie title cannot be null");
        Objects.requireNonNull(movieDto.getGenre(), "Movie genre cannot be null");
        String returnMessage;
        if (movieDto.getLength() <= 0 || movieDto.getLength() > 1260) {
            returnMessage = "Please provide a valid movie length";
        } else {
            Movie movie = new Movie(null, movieDto.getTitle(), movieDto.getGenre(), movieDto.getLength());
            movieRepository.save(movie);
            returnMessage = (movie + " successfully created.");
        }
        return returnMessage;

    }

    @Override
    public String updateMovie(MovieDto movieDto) {
        Objects.requireNonNull(movieDto, "Movie cannot be null");
        Objects.requireNonNull(movieDto.getTitle(), "Movie title cannot be null");
        Objects.requireNonNull(movieDto.getGenre(), "Movie genre cannot be null");
        if (movieDto.getLength() <= 0 || movieDto.getLength() > 1260) {
            return "Please provide a valid movie length";
        }
        if (movieRepository.findByTitle(movieDto.getTitle()).isPresent()) {
            Optional<Movie> movie = movieRepository.findByTitle(movieDto.getTitle());
            movie = Optional.of(new Movie(
                    movie.get().getId(),
                    movieDto.getTitle(),
                    movieDto.getGenre(),
                    movieDto.getLength()));
            movieRepository.save(movie.get());
            return (movie.get().getTitle() + " were successfully updated");
        } else {
            if (movieRepository.findByTitle(movieDto.getTitle()).isEmpty()) {
                return "Cannot update a non-existing element";
            } else {
                return "Something went wrong";
            }
        }
    }

    public MovieDto convertEntityToDto(Movie movie) {
        return new MovieDto(movie.getTitle(), movie.getGenre(), movie.getLength());
    }
}

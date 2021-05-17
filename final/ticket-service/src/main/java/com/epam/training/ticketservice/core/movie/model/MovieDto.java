package com.epam.training.ticketservice.core.movie.model;

import java.util.Objects;

public class MovieDto {
    private String title;
    private String genre;
    private int length;

    public MovieDto(String title, String genre, int length) {
        this.title = title;
        this.genre = genre;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieDto movieDto = (MovieDto) o;
        return length == movieDto.length
                && Objects.equals(title, movieDto.title)
                && Objects.equals(genre, movieDto.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, length);
    }

    @Override
    public String toString() {
        return "MovieDto{"
                + "title='"
                + title
                + '\''
                + ", genre='"
                + genre
                + '\''
                + ", length="
                + length
                + '}';
    }
}

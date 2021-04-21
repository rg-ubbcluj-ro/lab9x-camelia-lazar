package ro.ubb.cinema.repository;

import ro.ubb.cinema.domain.entities.Movie;

import java.util.List;

public interface MovieJDBCRepository extends Repository<Long, Movie> {

    List<Movie> getAllByNameContaining(String name);
    List<Movie> getAllByGenreContaining(String genre);


}

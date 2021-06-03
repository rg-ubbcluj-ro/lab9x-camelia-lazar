package ro.ubb.cinema.repository.movie;

import ro.ubb.cinema.domain.entities.Movie;
import ro.ubb.cinema.repository.Repository;

import java.util.List;

public interface MovieJDBCRepository extends Repository<Long, Movie> {
    List<Movie> getAllByNameContaining(String name);
    List<Movie> getAllByGenreContaining(String genre);
}

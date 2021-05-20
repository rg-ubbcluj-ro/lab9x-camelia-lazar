package ro.ubb.cinema.repository.movie;

import ro.ubb.cinema.domain.entities.Movie;

import java.util.List;

public interface MovieJDBCRepositoryCustom {
    List<Movie> customFindAllLevel1();
    Movie customFindOneLevel1(long id);
}

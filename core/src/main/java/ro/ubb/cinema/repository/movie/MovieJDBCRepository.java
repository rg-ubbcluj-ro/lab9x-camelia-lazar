package ro.ubb.cinema.repository.movie;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ro.ubb.cinema.domain.entities.Movie;
import ro.ubb.cinema.repository.Repository;

import java.util.List;

@Component("MovieJDBCRepositoryJPQL")
public interface MovieJDBCRepository extends Repository<Long, Movie>, MovieJDBCRepositoryCustom {
    @Query("SELECT DISTINCT m FROM Movie m WHERE m.id = ?1")
    @EntityGraph(value = "movieGraphDirect", type = EntityGraph.EntityGraphType.LOAD)
    Movie findOneDirect(long id);

    @Query("SELECT DISTINCT m FROM Movie m")
    @EntityGraph(value = "movieGraphDirect", type = EntityGraph.EntityGraphType.LOAD)
    List<Movie> findAllDirect();

    List<Movie> getAllByNameContaining(String name);
    List<Movie> getAllByGenreContaining(String genre);
}

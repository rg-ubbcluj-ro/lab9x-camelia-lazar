package ro.ubb.cinema.repository.cinema;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ro.ubb.cinema.domain.entities.Cinema;
import ro.ubb.cinema.repository.Repository;

import java.util.List;

@Component("CinemaJDBCRepositoryNativeSQL")
public interface CinemaJDBCRepository extends Repository<Long, Cinema>, CinemaJDBCRepositoryCustom {
    @Query("SELECT DISTINCT c FROM Cinema c WHERE c.id = ?1")
    @EntityGraph(value = "cinemaGraphDirect", type = EntityGraph.EntityGraphType.LOAD)
    Cinema findOneDirect(long identifier);

    @Query("SELECT DISTINCT c FROM Cinema c")
    @EntityGraph(value = "cinemaGraphDirect", type = EntityGraph.EntityGraphType.LOAD)
    List<Cinema> findAllDirect();

    List<Cinema> getAllByNameContaining(String name);
}

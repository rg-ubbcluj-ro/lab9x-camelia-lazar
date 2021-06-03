package ro.ubb.cinema.repository.room;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ro.ubb.cinema.domain.entities.Room;
import ro.ubb.cinema.repository.Repository;

import java.util.List;

@Component("RoomJDBCRepositoryNativeSQL")
public interface RoomJDBCRepository extends Repository<Long, Room>, RoomJDBCRepositoryCustom {
    @Query("SELECT DISTINCT r FROM Room r WHERE r.id = ?1")
    @EntityGraph(value = "roomGraphDirect", type = EntityGraph.EntityGraphType.LOAD)
    Room findOneDirect(long id);

    @Query("SELECT DISTINCT r FROM Room r")
    @EntityGraph(value = "roomGraphDirect", type = EntityGraph.EntityGraphType.LOAD)
    List<Room> findAllDirect();

    List<Room> getAllByNumberOfSeatsGreaterThanEqual(Integer numberOfSeats);

}

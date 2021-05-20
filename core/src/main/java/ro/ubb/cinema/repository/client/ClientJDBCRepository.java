package ro.ubb.cinema.repository.client;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import ro.ubb.cinema.domain.entities.Client;
import ro.ubb.cinema.repository.Repository;

import java.util.List;

@Component("ClientDBCRepositoryJPQL")
public interface ClientJDBCRepository extends Repository<Long, Client>, ClientJDBCRepositoryCustom {
    @Query("SELECT DISTINCT c FROM Client c WHERE c.id = ?1")
    @EntityGraph(value = "clientGraphDirect", type = EntityGraph.EntityGraphType.LOAD)
    Client findOneDirect(long id);

    @Query("SELECT DISTINCT c FROM Client c")
    @EntityGraph(value = "clientGraphDirect", type = EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllDirect();

    @Query("SELECT DISTINCT c FROM Client c")
    @EntityGraph(value = "clientGraphLevel2", type = EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllLevel2();

    List<Client> getAllByClientLastName(String name);

}

package ro.ubb.cinema.repository.room.JPQL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.cinema.domain.entities.Room;
import ro.ubb.cinema.repository.CustomRepositorySupport;
import ro.ubb.cinema.repository.room.RoomJDBCRepository;
import ro.ubb.cinema.repository.room.RoomJDBCRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component("RoomJDBCRepositoryJPQLImpl")
@NoRepositoryBean
public class RoomJDBCRepositoryImpl extends CustomRepositorySupport implements RoomJDBCRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(RoomJDBCRepository.class);

    @Override
    @Transactional
    public Room customFindOneLevel1(long id) {
        log.trace("Method [customFindOneLevel1] in {JPQL} started.");

        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "SELECT DISTINCT c FROM Client c " +
                        "LEFT JOIN FETCH c.cinemaId cc " +
                        "WHERE c.identifier = ?1"
        )
                .setParameter(1, id)
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("roomGraphLevel1"));

        Room room = (Room) query.getSingleResult();
        log.trace("Method [customFindOneLevel1] in {JPQL} ended with result = {}.", room);

        return room;
    }

    @Override
    @Transactional
    public List<Room> customFindAllLevel1() {
        log.trace("Method [customFindAllLevel1] in {JPQL} started.");

        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "SELECT DISTINCT r FROM Room r " +
                        "LEFT JOIN FETCH r.cinemaId cc"
        )
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("roomGraphLevel1"));

        @SuppressWarnings("unchecked")
        List<Room> rooms = query.getResultList();
        log.trace("Method [customFindAllLevel1] in {JPQL} ended with result = {}.", rooms);
        return rooms;
    }
}

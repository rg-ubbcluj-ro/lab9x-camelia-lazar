package ro.ubb.cinema.repository.room.CriteriaAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.cinema.domain.entities.Room;
import ro.ubb.cinema.domain.entities.Room_;
import ro.ubb.cinema.repository.CustomRepositorySupport;
import ro.ubb.cinema.repository.movie.MovieJDBCRepository;
import ro.ubb.cinema.repository.room.RoomJDBCRepository;
import ro.ubb.cinema.repository.room.RoomJDBCRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Component("RoomJDBCRepositoryCriteriaAPIImpl")
@NoRepositoryBean
public class RoomJDBCRepositoryImpl extends CustomRepositorySupport implements RoomJDBCRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(RoomJDBCRepository.class);

    @Override
    @Transactional
    public Room customFindOneLevel1(long id) {
        log.trace("Method [customFindOneLevel1] in {CriteriaAPI} started.");

        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
        query.distinct(Boolean.TRUE);
        Root<Room> root = query.from(Room.class);
        query.where(criteriaBuilder.equal(root.get("identifier"), id));
        query.select(root);
        root.fetch(Room_.cinema, JoinType.LEFT);

        Room room  = entityManager.createQuery(query)
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("roomGraphLevel1"))
                .getSingleResult();

        log.trace("Method [customFindOneLevel1] in {CriteriaAPI} ended with result = {}.", room);

        return room;
    }

    @Override
    @Transactional
    public List<Room> customFindAllLevel1() {
        log.trace("Method [customFindAllLevel1] in {CriteriaAPI} started.");

        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
        query.distinct(Boolean.TRUE);
        Root<Room> root = query.from(Room.class);
        root.fetch(Room_.cinema, JoinType.LEFT);

        List<Room> rooms = entityManager.createQuery(query).setHint("javax.persistence.roomGraphLevel1",
                entityManager.getEntityGraph("roomGraphDirect")).getResultList();

        log.trace("Method [customFindAllLevel1] in {CriteriaAPI} ended with result = {}.", rooms);

        return rooms;
    }
}

package ro.ubb.cinema.repository.room.NativeSQL;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.cinema.domain.entities.Room;
import ro.ubb.cinema.repository.CustomRepositorySupport;
import ro.ubb.cinema.repository.room.RoomJDBCRepository;
import ro.ubb.cinema.repository.room.RoomJDBCRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component("RoomJDBCRepositoryNativeSQLImpl")
public class RoomJDBCRepositoryImpl extends CustomRepositorySupport implements RoomJDBCRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(RoomJDBCRepository.class);

    @Transactional
    @Override
    public Room customFindOneLevel1(long id) {
        log.trace("Method [customFindOneLevel1] in {NativeSQL} started.");

        EntityManager entityManager = getEntityManager();
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        Query query = session.createSQLQuery("SELECT DISTINCT {a.*}, {b.*} " +
                "FROM Room a " +
                "LEFT JOIN Cinema b on a.identifier = b.cinemaId " +
                "WHERE a.identifier = ?1")
                .setParameter(1, id)
                .addEntity("a",Room.class)
                .addJoin("b", "a.cinema")
                .addEntity("a",Room.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        Room room = (Room) query.getSingleResult();
        log.trace("Method [customFindOneLevel1] in {NativeSQL} ended with result = {}.", room);
        return room;
    }

    @Override
    @Transactional
    public List<Room> customFindAllLevel1() {
        log.trace("Method [customFindAllLevel1] in {NativeSQL} started.");

        EntityManager entityManager = getEntityManager();
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        Query query = session.createSQLQuery("SELECT DISTINCT {a.*}, {b.*} " +
                "FROM Room a " +
                "LEFT JOIN Cinema b on a.identifier = b.cinemaId")
                .addEntity("a",Room.class)
                .addJoin("b", "a.cinema")
                .addEntity("a",Room.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Room> rooms = query.getResultList();
        log.trace("Method [customFindAllLevel1] in {NativeSQL} ended with result = {}.", rooms);
        return rooms;
    }
}

package ro.ubb.cinema.repository.cinema.NativeSQL;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.cinema.domain.entities.Cinema;
import ro.ubb.cinema.repository.CustomRepositorySupport;
import ro.ubb.cinema.repository.cinema.CinemaJDBCRepository;
import ro.ubb.cinema.repository.cinema.CinemaJDBCRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component("CinemaJDBCRepositoryNativeSQLImpl")
public class CinemaJDBCRepositoryImpl  extends CustomRepositorySupport implements CinemaJDBCRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(CinemaJDBCRepository.class);

    @Transactional
    @Override
    public Cinema customFindOneLevel1(long id) {
        log.trace("Method [customFindOneLevel1] in {NativeSQL} started.");

        EntityManager entityManager = getEntityManager();
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        Query query = session.createSQLQuery("SELECT DISTINCT {c.*}" +
                "FROM Cinema c " +
                "WHERE c.identifier = ?1")
                .setParameter(1, id)
                .addEntity("c", Cinema.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        Cinema cinema = (Cinema) query.getSingleResult();
        log.trace("Method [customFindOneLevel1] in {NativeSQL} ended with result = {}.", cinema);

        return cinema;
    }

    @Override
    @Transactional
    public List<Cinema> customFindAllLevel1() {
        log.trace("Method [customFindAllLevel1] in {NativeSQL} started.");

        EntityManager entityManager = getEntityManager();
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        Query query = session.createSQLQuery("SELECT DISTINCT {c.*}" +
                "FROM Cinema c ")
                .addEntity("c",Cinema.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Cinema> cinemas = query.getResultList();

        log.trace("Method [customFindAllLevel1] in {NativeSQL} ended with result = {}.", cinemas);

        return cinemas;
    }
}

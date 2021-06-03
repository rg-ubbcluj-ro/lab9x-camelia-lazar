package ro.ubb.cinema.repository.cinema.JPQL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.cinema.domain.entities.Cinema;
import ro.ubb.cinema.repository.CustomRepositorySupport;
import ro.ubb.cinema.repository.cinema.CinemaJDBCRepository;
import ro.ubb.cinema.repository.cinema.CinemaJDBCRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component("CinemaJDBCRepositoryJPQLImpl")
@NoRepositoryBean
public class CinemaJDBCRepositoryImpl extends CustomRepositorySupport implements CinemaJDBCRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(CinemaJDBCRepository.class);

    @Override
    @Transactional
    public Cinema customFindOneLevel1(long id) {
        log.trace("Method [customFindOneLevel1] in {JPQL} started.");
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "SELECT DISTINCT c FROM Cinema c WHERE c.identifier = ?1"
        )
                .setParameter(1, id)
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("cinemaGraphDirect"));

        Cinema cinema = (Cinema) query.getSingleResult();
        log.trace("Method [customFindOneLevel1] in {JPQL} ended with result = {}.", cinema);

        return cinema;
    }

    @Override
    @Transactional
    public List<Cinema> customFindAllLevel1() {
        log.trace("Method [customFindAllLevel1] in {JPQL} started.");
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "SELECT DISTINCT c FROM Cinema c"
        )
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("cinemaGraphDirect"));

        @SuppressWarnings("unchecked")
        List<Cinema> cinemas = query.getResultList();
        log.trace("Method [customFindAllLevel1] in {JPQL} ended with result = {}.", cinemas);

        return cinemas;
    }
}

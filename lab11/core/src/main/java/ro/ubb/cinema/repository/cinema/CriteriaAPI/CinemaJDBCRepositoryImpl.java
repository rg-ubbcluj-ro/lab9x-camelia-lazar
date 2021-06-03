package ro.ubb.cinema.repository.cinema.CriteriaAPI;

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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component("CinemaJDBCRepositoryCriteriaAPIImpl")
@NoRepositoryBean
public class CinemaJDBCRepositoryImpl extends CustomRepositorySupport implements CinemaJDBCRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(CinemaJDBCRepository.class);

    @Override
    @Transactional
    public Cinema customFindOneLevel1(long id) {
        log.trace("Method [customFindOneLevel1] in {CriteriaAPI} started.");
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cinema> query = criteriaBuilder.createQuery(Cinema.class);
        query.distinct(Boolean.TRUE);
        Root<Cinema> root = query.from(Cinema.class);
        query.where(criteriaBuilder.equal(root.get("identifier"), id));
        query.select(root);

        Cinema cinema = entityManager.createQuery(query)
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("cinemaGraphDirect"))
                .getSingleResult();

        log.trace("Method [customFindOneLevel1] in {CriteriaAPI} ended with result = {}.", cinema);

        return cinema;
    }

    @Override
    @Transactional
    public List<Cinema> customFindAllLevel1() {
        log.trace("Method [customFindAllLevel1] in {CriteriaAPI} started.");
        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cinema> query = criteriaBuilder.createQuery(Cinema.class);
        query.distinct(Boolean.TRUE);
        Root<Cinema> root = query.from(Cinema.class);

        List<Cinema> cinemas = entityManager.createQuery(query).setHint("javax.persistence.fetchgraph",
                entityManager.getEntityGraph("cinemaGraphDirect")).getResultList();

        log.trace("Method [customFindAllLevel1] in {CriteriaAPI} ended with result = {}.", cinemas);


        return cinemas;
    }
}

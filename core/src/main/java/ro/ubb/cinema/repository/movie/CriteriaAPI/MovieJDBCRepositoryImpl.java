package ro.ubb.cinema.repository.movie.CriteriaAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.cinema.domain.entities.Movie;
import ro.ubb.cinema.domain.entities.Movie_;
import ro.ubb.cinema.repository.CustomRepositorySupport;
import ro.ubb.cinema.repository.client.ClientJDBCRepository;
import ro.ubb.cinema.repository.movie.MovieJDBCRepository;
import ro.ubb.cinema.repository.movie.MovieJDBCRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Component("MovieJDBCRepositoryCriteriaAPIImpl")
@NoRepositoryBean
public class MovieJDBCRepositoryImpl extends CustomRepositorySupport implements MovieJDBCRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(MovieJDBCRepository.class);

    @Override
    @Transactional
    public Movie customFindOneLevel1(long id) {
        log.trace("Method [customFindOneLevel1] in {CriteriaAPI} started.");

        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = criteriaBuilder.createQuery(Movie.class);
        query.distinct(Boolean.TRUE);
        Root<Movie> root = query.from(Movie.class);
        query.where(criteriaBuilder.equal(root.get("identifier"), id));
        query.select(root);
        root.fetch(Movie_.tickets, JoinType.LEFT);
        root.fetch(Movie_.trailer);

        Movie movie = entityManager.createQuery(query)
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("movieGraphLevel1"))
                .getSingleResult();

        log.trace("Method [customFindOneLevel1] in {CriteriaAPI} ended with result = {}.", movie);

        return movie;
    }

    @Override
    @Transactional
    public List<Movie> customFindAllLevel1() {
        log.trace("Method [customFindAllLevel1] in {CriteriaAPI} started.");

        EntityManager entityManager = getEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = criteriaBuilder.createQuery(Movie.class);
        query.distinct(Boolean.TRUE);
        Root<Movie> root = query.from(Movie.class);
        root.fetch(Movie_.tickets, JoinType.LEFT);
        root.fetch(Movie_.trailer);

        List<Movie> movies = entityManager.createQuery(query).setHint("javax.persistence.fetchgraph",
                entityManager.getEntityGraph("movieGraphLevel1")).getResultList();

        log.trace("Method [customFindAllLevel1] in {CriteriaAPI} ended with result = {}.", movies);

        return movies;
    }
}

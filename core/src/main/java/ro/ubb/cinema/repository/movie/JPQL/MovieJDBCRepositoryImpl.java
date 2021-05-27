package ro.ubb.cinema.repository.movie.JPQL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.cinema.domain.entities.Movie;
import ro.ubb.cinema.repository.CustomRepositorySupport;
import ro.ubb.cinema.repository.movie.MovieJDBCRepository;
import ro.ubb.cinema.repository.movie.MovieJDBCRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component("MovieJDBCRepositoryJPQLImpl")
public class MovieJDBCRepositoryImpl extends CustomRepositorySupport implements MovieJDBCRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(MovieJDBCRepository.class);

    @Override
    @Transactional
    public Movie customFindOneLevel1(long id) {
        log.trace("Method [customFindOneLevel1] in {JPQL} started.");

        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "SELECT DISTINCT m FROM Movie m " +
                        "LEFT JOIN FETCH m.tickets t " +
                        "WHERE m.identifier = ?1"
        )
                .setParameter(1, id)
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("movieGraphLevel1"));

        Movie movie = (Movie) query.getSingleResult();
        log.trace("Method [customFindOneLevel1] in {JPQL} ended with result = {}.", movie);

        return movie;
    }

    @Override
    @Transactional
    public List<Movie> customFindAllLevel1() {
        log.trace("Method [customFindAllLevel1] in {JPQL} started.");

        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "SELECT DISTINCT m FROM Movie m " +
                        "LEFT JOIN FETCH m.tickets t"
        )
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("movieGraphLevel1"));

        @SuppressWarnings("unchecked")
        List<Movie> movies = query.getResultList();
        log.trace("Method [customFindAllLevel1] in {JPQL} ended with result = {}.", movies);

        return movies;
    }
}

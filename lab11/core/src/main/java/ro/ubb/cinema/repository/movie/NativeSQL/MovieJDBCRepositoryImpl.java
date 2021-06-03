package ro.ubb.cinema.repository.movie.NativeSQL;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.cinema.domain.entities.Movie;
import ro.ubb.cinema.repository.CustomRepositorySupport;
import ro.ubb.cinema.repository.movie.MovieJDBCRepository;
import ro.ubb.cinema.repository.movie.MovieJDBCRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component("MovieJDBCRepositoryNativeSQLImpl")
public class MovieJDBCRepositoryImpl  extends CustomRepositorySupport implements MovieJDBCRepositoryCustom {
    private static final Logger log = LoggerFactory.getLogger(MovieJDBCRepository.class);

    @Transactional
    @Override
    public Movie customFindOneLevel1(long id) {
        log.trace("Method [customFindOneLevel1] in {NativeSQL} started.");

        EntityManager entityManager = getEntityManager();
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        Query query = session.createSQLQuery("SELECT DISTINCT {a.*}, {b.*} " +
                "FROM Movie a " +
                "LEFT JOIN Ticket b on a.identifier = b.movieid" +
                "WHERE a.identifier = ?1")
                .setParameter(1, id)
                .addEntity("a",Movie.class)
                .addJoin("b", "a.tickets")
                .addEntity("a",Movie.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        Movie movie = (Movie) query.getSingleResult();
        log.trace("Method [customFindOneLevel1] in {NativeSQL} ended with result = {}.", movie);

        return movie;
    }

    @Override
    @Transactional
    public List<Movie> customFindAllLevel1() {
        log.trace("Method [customFindAllLevel1] in {NativeSQL} started.");

        EntityManager entityManager = getEntityManager();
        HibernateEntityManager hibernateEntityManager = getEntityManager().unwrap(HibernateEntityManager.class);
        Session session = hibernateEntityManager.getSession();
        Query query = session.createSQLQuery("SELECT DISTINCT {a.*}, {b.*} " +
                "FROM Movie a " +
                "LEFT JOIN Ticket b on a.identifier = b.movieid")
                .addEntity("a",Movie.class)
                .addJoin("b", "a.tickets")
                .addEntity("a",Movie.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Movie> movies = query.getResultList();
        log.trace("Method [customFindAllLevel1] in {NativeSQL} ended with result = {}.", movies);

        return movies;
    }
}

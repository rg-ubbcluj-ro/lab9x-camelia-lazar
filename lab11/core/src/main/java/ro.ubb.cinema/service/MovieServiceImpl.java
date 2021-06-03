package ro.ubb.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cinema.domain.entities.Movie;
import ro.ubb.cinema.domain.validators.MovieValidator;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.repository.client.ClientJDBCRepository;
import ro.ubb.cinema.repository.movie.MovieJDBCRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service for managing Movies
 *
 * @author camelia-lazar
 */

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieJDBCRepository repository;

    @Autowired
    private ClientJDBCRepository clientRepository;

    private final MovieValidator movieValidator = new MovieValidator();
    private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);


    @Override
    public Movie saveMovie(Movie movie) throws ValidatorException {
        log.trace("addMovie - method entered: movie={}", movie);
        movieValidator.validate(movie);
        Movie returnedMovie = repository.save(movie);
        log.trace("addMovie - method finished");
        return returnedMovie;
    }

    @Override
    public void deleteMovie(Long id) {
        log.trace("deleteMovie - method entered: movieId={}", id);
        Movie movie = repository.customFindOneLevel1(id);

        movie.getTickets()
                .forEach( t -> {
                    t.getClient().deleteTicket(id);
                    this.clientRepository.save(t.getClient());
                });

        repository.deleteById(id);
        log.trace("deleteMovie - method finished");
    }

    @Override
    @Transactional
    public Movie updateMovie(Movie movie) {
        log.trace("updateMovie - method entered: movie={}", movie);
        movieValidator.validate(movie);
        Movie updateMovie = repository.customFindOneLevel1(movie.getId());
        updateMovie.setName(movie.getName());
        updateMovie.setGenre(movie.getGenre());
        updateMovie.setDuration(movie.getDuration());
        log.trace("updateMovie - method finished");
        return movie;
    }

    @Override
    public List<Movie> getAllMovies() {
        log.trace("getAllMovies - method entered");

        List<Movie> movies = repository.customFindAllLevel1();

        log.trace("getAllMovies - method finished: movies={}", movies);
        return movies;
    }

   @Override
    public List<Movie> filterMoviesByName(String nameToFilter) {
       log.trace("filterMoviesByName - method entered: nameToFilter={}", nameToFilter);
       Iterable<Movie> movies = repository.customFindAllLevel1();

        List<Movie> filteredMovies = repository.getAllByNameContaining(nameToFilter);

       log.trace("filterMoviesByName - method finished: filteredMovies={}", filteredMovies);

       return filteredMovies;
    }

    @Override
    public List<Movie> filterMoviesByGenre(String genre) {
        log.trace("filterMoviesByGenre - method entered: genre={}", genre);

        List<Movie> filteredMovies = repository.getAllByGenreContaining(genre);

        log.trace("filterMoviesByGenre - method finished: filteredMovies={}", filteredMovies);

        return filteredMovies;
    }

    @Override
    public Movie getMovieById(Long id) {
        return repository.customFindOneLevel1(id);
    }
}

package ro.ubb.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cinema.domain.entities.Animation;
import ro.ubb.cinema.domain.validators.AnimationValidator;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.repository.AnimationJDBCRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service for managing Movies
 *
 * @author camelia-lazar
 */

@Service
public class AnimationServiceImpl implements AnimationService {
    @Autowired
    private AnimationJDBCRepository repository;

    private final AnimationValidator animationValidator = new AnimationValidator();
    private static final Logger log = LoggerFactory.getLogger(AnimationServiceImpl.class);


    @Override
    public Animation saveAnimation(Animation animation) throws ValidatorException {
        log.trace("addAnimation - method entered: animation={}", animation);
        animationValidator.validate(animation);
        Animation returnedAnimation = repository.save(animation);
        log.trace("saveAnimation - method finished");
        return returnedAnimation;
    }

    @Override
    public void deleteAnimation(Long id) {
        log.trace("deleteAnimation - method entered: animationId={}", id);
            repository.deleteById(id);
        log.trace("deleteAnimation - method finished");
    }

    @Override
    @Transactional
    public Animation updateAnimation(Animation animation) {
        log.trace("updateMovie - method entered: movie={}", animation);
        animationValidator.validate(animation);
        Animation updateAnimation = repository.findById(animation.getId()).orElseThrow();
        updateAnimation.setName(animation.getName());
        updateAnimation.setGenre(animation.getGenre());
        updateAnimation.setDuration(animation.getDuration());
        log.trace("updateMovie - method finished");
        return animation;
    }

    @Override
    public List<Animation> getAllAnimations() {
        log.trace("getAllAnimations - method entered");

        List<Animation> animations = repository.findAll();

        log.trace("getAllAnimations - method finished: animations={}", animations);
        return animations;
    }

   @Override
    public List<Animation> filterAnimationsByName(String nameToFilter) {
       log.trace("filterMoviesByName - method entered: nameToFilter={}", nameToFilter);
       Iterable<Animation> animations = repository.findAll();

        List<Animation> filteredAnimations = repository.getAllByNameContaining(nameToFilter);

       log.trace("filterAnimationsByName - method finished: filteredAnimations={}", filteredAnimations);

       return filteredAnimations;
    }

    @Override
    public List<Animation> filterAnimationsByGenre(String genre) {
        log.trace("filterAnimationsByGenre - method entered: genre={}", genre);

        List<Animation> filteredAnimations = repository.getAllByGenreContaining(genre);

        log.trace("filterAnimationsByGenre - method finished: filteredAnimations={}", filteredAnimations);

        return filteredAnimations;
    }
//
//    @Override
//    public Set<Movie> getLongestMovies() {
//        log.trace("filterMoviesByName - method entered");
//
//        Iterable<Movie> allMovies = repository.findAll();
//
//        Set<Movie> movies = new HashSet<>();
//        allMovies.forEach(movies::add);
//
//        int maxDuration = movies.stream().mapToInt(Movie::getDuration)
//                .max().orElseThrow(RuntimeException::new);
//
//        movies.removeIf(movie -> !movie.getDuration().equals(maxDuration));
//
//        log.trace("filterMoviesByName - method finished: longestMovies={}", movies);
//
//        return movies;
//    }
//
//    @Override
//    public Boolean containsOne(Long identifier)
//    {
//        log.trace("containsOne - method entered");
//
//        Boolean result = this.repository.findById(identifier).isPresent();
//
//        log.trace("containsOne - method finished: result={}", result);
//
//        return result;
//    }
//
//    @Override
//    public Movie get(Long identifier){
//        log.trace("get - method entered: identifier={}", identifier);
//
//        Optional<Movie> movie = this.repository.findById(identifier);
//        if (movie.isPresent())
//        {
//            log.trace("get - method finished");
//            return movie.get();
//        }
//        else
//        {
//            log.trace("get - exception found");
//            throw new ArrayIndexOutOfBoundsException("Movie not found");
//        }
//    }
}

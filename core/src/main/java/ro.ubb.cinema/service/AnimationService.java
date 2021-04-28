package ro.ubb.cinema.service;

import ro.ubb.cinema.domain.entities.Animation;
import ro.ubb.cinema.domain.entities.Movie;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;

import java.util.List;

public interface AnimationService {
    /**
     * Adds the given Animation to the Animations repository.
     *
     * @param Animation
     *            must not be null
     */
     Animation saveAnimation(Animation animation) throws ValidatorException;

    /**
     * Removes from the repository the Animation which has the same id as the given parameter
     *
     * @param id
     *            must not be null.
     */
     void deleteAnimation(Long id);

    /**
     * Updates in the repository the Animation which has the same id as the parameter
     *
     * @param Animation
     *            must not be null.
     */
     Animation updateAnimation(Animation animation) throws ValidatorException;

    /**
     *
     * @return all Animation entities.
     */
    List<Animation> getAllAnimations();


    /**
     * Returns all Animations whose name contain the given string.
     *
     * @param s - string which has to be contained in the name
     * @return an Set<Animation> - all the Animations which contains the string in the name
     */
    List<Animation> filterAnimationsByName(String s);
//
    /**
     * Returns all Animations whose name contain the given string.
     *
     * @param genre - the genre to be deleted
     * @return an Set<Animation> - all the Animations which contains the string in the name
     */
    List<Animation> filterAnimationsByGenre(String genre);
//
//    /**
//     * Returns the Animations with the maximum duration
//     *
//     * @return an Set<Animation> - the longest Animations
//     */
//     Set<Animation> getLongestAnimations();
//
//    /**
//     * Check if a Animation with the given identifier exists in the repository
//     * @param identifier - long, the identifier of the Animation searched for
//     * @return - boolean
//     *              true, if the repo contains a Animation with the given identifier
//     *              false, otherwise
//     */
//
//    Boolean containsOne(Long identifier);
//
//    /**
//     * Return the Animation that have the given identifier
//     * @param identifier - long, the identifier of the Animation searched for
//     * @return - Animation object
//     * @throws ArrayIndexOutOfBoundsException
//     *          if Animation not found
//     */
//    Animation get(Long identifier) throws ArrayIndexOutOfBoundsException;
}

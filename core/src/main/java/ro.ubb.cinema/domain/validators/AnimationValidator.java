package ro.ubb.cinema.domain.validators;

import ro.ubb.cinema.domain.entities.Animation;
import ro.ubb.cinema.domain.entities.Movie;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;

import java.util.Optional;

public class AnimationValidator implements Validator<Animation>{

    /**
     * Validate an object of type Movie
     * @param entity - Movie
     *               - the entity to be validated
     * @throws ValidatorException
     *                      if the entity is not valid
     */

    @Override
    public void validate(Animation entity) throws ValidatorException {

        Optional.of(entity)
                .filter(animation -> animation.getName().length() > 0)
                .orElseThrow(()-> {throw new ValidatorException("Movie name must contain at least one character");});

        Optional.of(entity)
                .filter(animation -> String.valueOf(animation.getDuration()).matches("^[0-9]+$") && animation.getDuration() >= 0)
                .orElseThrow(()-> {throw new ValidatorException("Invalid duration!");});

        Optional.of(entity)
                .filter(animation -> animation.getGenre().matches("^[a-zA-Z-_]+$"))
                .orElseThrow(()-> {throw new ValidatorException("Genre must contains only letters");});
    }
}

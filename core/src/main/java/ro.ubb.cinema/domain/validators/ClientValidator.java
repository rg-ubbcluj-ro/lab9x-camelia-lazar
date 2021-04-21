package ro.ubb.cinema.domain.validators;

import ro.ubb.cinema.domain.entities.Client;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;

import java.util.Optional;

public class ClientValidator implements Validator<Client>{

    /**
     * Validate an object of type Client
     * @param entity - Client
     *               - the entity to be validated
     * @throws ValidatorException
     *                      if the entity is not valid
     */
    @Override
    public void validate(Client entity) throws ValidatorException {

        Optional.ofNullable(entity.getClientFirstName())
                .filter(firstName -> firstName.matches("^[a-zA-Z-_]+$"))
                .orElseThrow( () -> new ValidatorException("Client's First Name is NOT valid: it must contain only letters."));

        Optional.ofNullable(entity.getClientLastName())
                .filter(lastName -> lastName.matches("^[a-zA-Z-_]+$"))
                .orElseThrow( () -> new ValidatorException("Client's Last Name is NOT valid: it must contain only letters."));

        Optional.ofNullable(entity.getClientEmail())
                .filter(email -> email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
                .orElseThrow( () -> new ValidatorException("Client's Email is NOT valid. Please try again."));

        Optional.ofNullable(entity.getClientAge())
                .filter(age -> String.valueOf(age).matches("^[0-9]+$") && age >= 0)
                .orElseThrow( () -> new ValidatorException("Client's Age is NOT valid: it has to be a positive number."));
    }
}

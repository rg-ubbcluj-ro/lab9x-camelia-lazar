package ro.ubb.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cinema.domain.entities.Client;
import ro.ubb.cinema.domain.validators.ClientValidator;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.repository.client.ClientJDBCRepository;
import ro.ubb.cinema.repository.movie.MovieJDBCRepository;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Service for managing Clients
 *
 * @author fiamardar
 */

@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientJDBCRepository repository;

    @Autowired
    private MovieJDBCRepository movieRepository;

    private final ClientValidator clientValidator = new ClientValidator();

    @Override
    public Client saveClient(Client client) throws ValidatorException {
        log.trace("addClient - method entered: client={}", client);
        clientValidator.validate(client);
        Client returnedClient = repository.save(client);
        log.trace("addClient - method finished");
        return returnedClient;
    }

    @Override
    public void deleteClient(Long id) {
        log.trace("deleteClient - method entered: clientId={}", id);

        Client client = repository.customFindOneLevel1(id);

        client.getTickets()
                .forEach( t -> {
                    t.getMovie().deleteTicket(id);
                    this.movieRepository.save(t.getMovie());
                });

        repository.deleteById(id);
        log.trace("deleteClient - method finished");
    }

    @Override
    @Transactional
    public Client updateClient(Client client) {
        log.trace("updateClient - method entered: client={}", client);
        clientValidator.validate(client);
        Client updateClient = repository.customFindOneLevel1(client.getId());
        updateClient.setClientFirstName(client.getClientFirstName());
        updateClient.setClientLastName(client.getClientLastName());
        updateClient.setClientEmail(client.getClientEmail());
        updateClient.setClientAge(client.getClientAge());
        log.trace("updateClient - method finished");

        return client;
    }

    @Override
    public List<Client> getAllClients() {
        log.trace("getAllClients - method entered");

        List<Client> clients = repository.customFindAllLevel1();

        log.trace("getAllClients - method finished: clients={}", clients);

        return clients;
    }

    @Override
    public List<Client> filterClientsByLastName(String lastNameToFilter) {
        log.trace("filterClientsByLastName - method entered: lastNameToFilter={}", lastNameToFilter);

        List<Client> filteredClients = repository.getAllByClientLastName(lastNameToFilter);

        log.trace("filterClientsByLastName - method finished: filteredClients={}", filteredClients);

        return filteredClients;
    }

    @Override
    public Client getClientById(Long id) {
        return repository.customFindOneLevel1(id);
    }

}

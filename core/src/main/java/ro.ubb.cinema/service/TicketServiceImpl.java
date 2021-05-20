package ro.ubb.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cinema.domain.entities.Client;
import ro.ubb.cinema.domain.entities.Movie;
import ro.ubb.cinema.domain.entities.Ticket;
import ro.ubb.cinema.domain.validators.TicketValidator;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.repository.client.ClientJDBCRepository;
import ro.ubb.cinema.repository.movie.MovieJDBCRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private ClientJDBCRepository clientRepository;
    @Autowired
    private MovieJDBCRepository movieRepository;

    private final TicketValidator ticketValidator = new TicketValidator();
    private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Override
    @Transactional
    public Ticket saveTicket(Ticket ticket) throws ValidatorException {
        log.trace("addTicket - method entered: ticket={}", ticket);
        ticketValidator.validate(ticket);

        Long movieId = ticket.getMovie().getId();
        Long clientId = ticket.getClient().getId();

        Movie movie = movieRepository.customFindAllLevel1().stream()
                .filter(m -> m.getId().equals(movieId))
                .collect(Collectors.toList()).get(0);

        Client client = clientRepository.customFindAllLevel1().stream()
                .filter(c -> c.getId().equals(clientId))
                .collect(Collectors.toList()).get(0);

        ticket.setMovie(movie);
        ticket.setClient(client);

        movie.addTicket(ticket);
        client.addTicket(ticket);

        movieRepository.save(movie);
        clientRepository.save(client);

        log.trace("addTicket - method finished");
        return ticket;
    }

    @Override
    @Transactional
    public void deleteTicket(Long id) {
        log.trace("deleteTicket - method entered: ticket {}", id);

        List<Ticket> tickets = this.getAllTickets();

        Ticket foundTicket = tickets.stream()
                .filter(t -> t.getId().equals(id))
                .collect(Collectors.toList()).get(0);

        Long movieId = foundTicket.getMovie().getId();
        Long clientId = foundTicket.getClient().getId();

        Movie movie = movieRepository.customFindAllLevel1().stream()
                .filter(m -> m.getId().equals(movieId))
                .collect(Collectors.toList()).get(0);

        Client client = clientRepository.customFindAllLevel1().stream()
                .filter(c -> c.getId().equals(clientId))
                .collect(Collectors.toList()).get(0);

        movie.deleteTicket(clientId);
        client.deleteTicket(movieId);

        movieRepository.save(movie);
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public Ticket updateTicket(Ticket ticket) throws ValidatorException {
        log.trace("updateTicket - method entered: ticket={}", ticket);
        ticketValidator.validate(ticket);

        Long movieId = ticket.getMovie().getId();
        Long clientId = ticket.getClient().getId();

        Movie movie = movieRepository.customFindAllLevel1().stream()
                .filter(m -> m.getId().equals(movieId))
                .collect(Collectors.toList()).get(0);

        Client client = clientRepository.customFindAllLevel1().stream()
                .filter(c -> c.getId().equals(clientId))
                .collect(Collectors.toList()).get(0);

        ticket.setMovie(movie);
        ticket.setClient(client);

        movie.updateTicket(ticket);
        client.updateTicket(ticket);

        movieRepository.save(movie);
        clientRepository.save(client);
        log.trace("updateCinema - method finished");
        return ticket;

    }

    @Override
    public List<Ticket> getAllTickets() {
        log.trace("getAllTickets - method entered");

        List<Client> clients = this.clientRepository.findAllLevel2();
        List<Ticket> tickets = clients.stream()
                .flatMap(play -> play.getTickets().stream())
                .collect(Collectors.toList());

        log.trace("getAllTickets - method finished: tickets={}", tickets);
        return tickets;
    }
}

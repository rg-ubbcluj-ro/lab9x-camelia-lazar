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
import ro.ubb.cinema.repository.ClientJDBCRepository;
import ro.ubb.cinema.repository.MovieJDBCRepository;
import ro.ubb.cinema.repository.RoomJDBCRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TicketServiceImpl implements TicketService {
//    @Autowired
//    private TicketJDBCRepository repository;
    @Autowired
    private RoomJDBCRepository roomRepository;
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

        Movie movie = movieRepository.findAll().stream()
                .filter(m -> m.getId().equals(movieId))
                .collect(Collectors.toList()).get(0);

        Client client = clientRepository.findAll().stream()
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

        Movie movie = movieRepository.findAll().stream()
                .filter(m -> m.getId().equals(movieId))
                .collect(Collectors.toList()).get(0);

        Client client = clientRepository.findAll().stream()
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

        Movie movie = movieRepository.findAll().stream()
                .filter(m -> m.getId().equals(movieId))
                .collect(Collectors.toList()).get(0);

        Client client = clientRepository.findAll().stream()
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

        List<Client> clients = this.clientRepository.findAll();
        List<Ticket> tickets = clients.stream()
                .flatMap(play -> play.getTickets().stream())
                .collect(Collectors.toList());

        log.trace("getAllTickets - method finished: tickets={}", tickets);
        return tickets;
    }

//    @Override
//    public List<Ticket> filterTicketsByPrice(Double price) {
//        log.trace("filterTicketsByPrice - method entered: price={}", price);
//
//        List<Ticket> filteredTickets = repository.getAllByPriceLessThanEqual(price);
//
//        log.trace("filterTicketsByPrice - method finished: filteredTickets={}", filteredTickets);
//        return filteredTickets;
//    }
//
//    @Override
//    public void deleteTicketsByDate(LocalDate date){
//        log.trace("deleteTicketsByDate - method entered: date={}", date);
//        Iterable<Ticket> tickets = repository.findAll();
//
//        Set<Ticket> filteredTickets = new HashSet<>();
//        tickets.forEach(filteredTickets::add);
//        filteredTickets.removeIf(ticket -> (!ticket.getDate().equals(date)));
//
//        filteredTickets.forEach(ticket -> repository.deleteById(ticket.getId()));
//        log.trace("deleteTicketsByDate - method finished");
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
//    public Ticket get(Long identifier){
//        log.trace("get - method entered: identifier={}", identifier);
//
//        Optional<Ticket> ticket = this.repository.findById(identifier);
//        if (ticket.isPresent())
//        {
//            log.trace("get - method finished");
//            return ticket.get();
//        }
//        else
//        {
//            log.trace("get - exception found");
//            throw new ArrayIndexOutOfBoundsException("Ticket not found");
//        }
//    }
//
//    private void getRoom(Ticket ticket) {
//        log.trace("getRoom - method entered: ticket={}", ticket);
//        Optional<Room> room = this.roomRepository.findById(ticket.getRoom().getId());
//        if (room.isPresent())
//            ticket.setRoom(room.get());
//        else
//            throw new ServiceValidatorException("Room ID does not exists.");
//        log.trace("getRoom - method finished");
//    }
//
//    private void getClient(Ticket ticket) {
//        log.trace("getClient - method entered: ticket={}", ticket);
//
//        Optional<Client> client = this.clientRepository.findById(ticket.getClient().getId());
//        if (client.isPresent())
//            ticket.setClient(client.get());
//        else
//            throw new ServiceValidatorException("Client ID does not exists.");
//        log.trace("getClient - method finished");
//    }
//
//    private void getMovie(Ticket ticket) {
//        log.trace("getMovie - method entered: ticket={}", ticket);
//
//        Optional<Movie> movie = this.movieRepository.findById(ticket.getMovie().getId());
//        if (movie.isPresent())
//            ticket.setMovie(movie.get());
//        else
//            throw new ServiceValidatorException("Movie ID does not exists.");
//        log.trace("getMovie - method finished");
//    }
//
//    private void getAllTicketRelations(Ticket ticket){
//        log.trace("getAllTicketRelations - method entered");
//        getRoom(ticket);
//        getClient(ticket);
//        getMovie(ticket);
//        log.trace("getAllTicketRelations - method finished");
//    }
//
//    @Override
//    public Repository<Long, Ticket> getRepository() {
//        log.trace("getRepository - method entered and finished");
//        return repository;
//    }
}

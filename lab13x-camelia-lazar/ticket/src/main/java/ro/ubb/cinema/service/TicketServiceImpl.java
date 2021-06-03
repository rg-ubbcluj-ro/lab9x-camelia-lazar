package ro.ubb.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cinema.domain.entities.Ticket;
import ro.ubb.cinema.repository.ticket.TicketJDBCRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for managing Movies
 *
 * @author camelia-lazar
 */

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketJDBCRepository ticketRepository;

    private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Override
    @Transactional
    public Ticket saveTicket(Ticket ticket) {
        log.trace("addTicket - method entered: ticket={}", ticket);;

        ticketRepository.save(ticket);

        log.trace("addTicket - method finished");
        return ticket;
    }

    @Override
    @Transactional
    public void deleteTicket(Long id) {
        log.trace("deleteTicket - method entered: ticket {}", id);

        ticketRepository.deleteById(id);

        log.trace("deleteTicket - method finished: ticket {}", id);
    }

    @Override
    @Transactional
    public Ticket updateTicket(Ticket ticket) {
        log.trace("updateTicket - method entered: ticket={}", ticket);

        Ticket ticketFromRepo = ticketRepository.findById(ticket.getId()).get();
        ticketFromRepo.setPrice(ticket.getPrice());
        ticketRepository.save(ticketFromRepo);

        log.trace("updateCinema - method finished");
        return ticket;

    }
    @Override
    public List<Ticket> getAllTickets() {
        log.trace("getAllTickets - method entered");

        List<Ticket> tickets = ticketRepository.findAll();

        log.trace("getAllTickets - method finished: tickets={}", tickets);
        return tickets;
    }
}

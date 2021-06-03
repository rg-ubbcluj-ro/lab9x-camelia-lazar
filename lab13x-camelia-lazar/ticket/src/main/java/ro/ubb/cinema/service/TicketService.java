package ro.ubb.cinema.service;


import ro.ubb.cinema.domain.entities.Ticket;

import java.util.List;

public interface TicketService {
    /**
     * Returns all the tickets of a repository
     *
     * @return all tickets.
     */
    List<Ticket> getAllTickets();

    /**
     * Adds the given ticket.
     *
     * @param ticket must not be null.
     * @throws IllegalArgumentException if the given entity is null.
     */
    Ticket saveTicket(Ticket ticket);

    /**
     * Removes the given ticket.
     *
     * @param id - id of the ticket
     *           <p>
     *           must not be null.
     */
    void deleteTicket(Long id);

    /**
     * Updates the given ticket.
     *
     * @param ticket must not be null.
     */
    Ticket updateTicket(Ticket ticket);
}


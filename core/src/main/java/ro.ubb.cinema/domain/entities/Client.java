package ro.ubb.cinema.domain.entities;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for the Client entity
 *
 * @author fiamardar
 */

@Entity(name = "Client")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"tickets"})
@ToString(callSuper = true, exclude = {"tickets"})
@Table(name = "client")
public class Client extends BaseEntity<Long>{
    @Column(name="firstName")
	private String clientFirstName;
    @Column(name="lastName")
    private String clientLastName;
    @Column(name="email")
	private String clientEmail;
    @Column(name="age")
	private Integer clientAge;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> tickets;

    public Client(Long clientId) {
        super(clientId);
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void deleteTicket(Long movieId) {
        Ticket ticketToBeDeleted = this.tickets.stream()
                .filter(t ->
                        t.getMovie().getId().equals(movieId))
                .collect(Collectors.toList())
                .get(0);

        this.tickets.remove(ticketToBeDeleted);
    }

    public void updateTicket(Ticket ticket) {
        Ticket addedTicket = this.tickets.stream()
                .filter(t ->
                        t.getMovie().getId().equals(ticket.getMovie().getId()))
                .collect(Collectors.toList())
                .get(0);
        addedTicket.setPrice(ticket.getPrice());
    }

    public Set<Ticket> getTickets() {
        tickets = this.tickets == null ? new HashSet<>() : this.tickets;
        return tickets;
    }
}

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

@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "clientGraphDirect",
                attributeNodes = {
                        @NamedAttributeNode("clientFirstName"),
                        @NamedAttributeNode("clientLastName"),
                        @NamedAttributeNode("clientEmail"),
                        @NamedAttributeNode("clientAge"),
                }
        ),
        @NamedEntityGraph(
                name = "clientGraphLevel1",
                attributeNodes = {
                        @NamedAttributeNode("clientFirstName"),
                        @NamedAttributeNode("clientLastName"),
                        @NamedAttributeNode("clientEmail"),
                        @NamedAttributeNode("clientAge"),
                        @NamedAttributeNode(
                                value = "tickets",
                                subgraph = "ticketsSubGraphDirect"
                        )
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "ticketsSubgraphDirect",
                                attributeNodes = {
                                        @NamedAttributeNode("price"),
                                        @NamedAttributeNode("date"),
                                        @NamedAttributeNode("time")
                                }
                        )
                }
        ),
        @NamedEntityGraph(
                name = "clientGraphLevel2",
                attributeNodes = {
                        @NamedAttributeNode("clientFirstName"),
                        @NamedAttributeNode("clientLastName"),
                        @NamedAttributeNode("clientEmail"),
                        @NamedAttributeNode("clientAge"),
                        @NamedAttributeNode(
                                value = "tickets",
                                subgraph = "ticketsSubGraphLevel1"
                        )
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "ticketsSubGraphLevel1",
                                attributeNodes = {
                                        @NamedAttributeNode("price"),
                                        @NamedAttributeNode("date"),
                                        @NamedAttributeNode("time"),
                                        @NamedAttributeNode(
                                                value = "movie",
                                                subgraph = "movieSubGraphDirect"
                                        )
                                }
                        ),
                       @NamedSubgraph(
                               name = "movieSubGraphDirect",
                               attributeNodes = {
                                       @NamedAttributeNode("name"),
                                       @NamedAttributeNode("duration"),
                                       @NamedAttributeNode("genre"),
                               }
                       )
                }
        )
})
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

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
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

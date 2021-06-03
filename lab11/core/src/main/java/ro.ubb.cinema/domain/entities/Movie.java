package ro.ubb.cinema.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for the Movie entity
 *
 * @author camelia-lazar
 */

@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "movieGraphDirect",
                attributeNodes = {
                        @NamedAttributeNode("name"),
                        @NamedAttributeNode("duration"),
                        @NamedAttributeNode("genre"),
                }
        ),
        @NamedEntityGraph(
                name = "movieGraphLevel1",
                attributeNodes = {
                        @NamedAttributeNode("name"),
                        @NamedAttributeNode("duration"),
                        @NamedAttributeNode("genre"),
                        @NamedAttributeNode(
                                value = "tickets",
                                subgraph = "ticketsSubGraphDirect"
                        ),
                        @NamedAttributeNode(
                                value = "trailer",
                                subgraph = "trailerSubGraphDirect"
                        ),
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "ticketsSubgraphDirect",
                                attributeNodes = {
                                        @NamedAttributeNode("price"),
                                        @NamedAttributeNode("date"),
                                        @NamedAttributeNode("time")
                                }
                        ),
                        @NamedSubgraph(
                                name = "trailerSubGraphDirect",
                                attributeNodes = {
                                        @NamedAttributeNode("publishingYear"),
                                        @NamedAttributeNode("soundtrack"),
                                }
                        )
                }
        )
})
@Entity(name = "Movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"trailer", "tickets"})
@ToString(callSuper = true, exclude = {"trailer", "tickets"})
@Table(name = "movie")
public class Movie extends BaseEntity<Long>{
    @Column(name="name")
    private String name;
    @Column(name="duration")
    private Integer duration;
    @Column(name="genre")
    private String genre;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> tickets;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "publishingYear", column = @Column(name = "publishingYear")),
            @AttributeOverride(name = "soundtrack", column = @Column(name = "soundtrack")),
    })
    private Trailer trailer;

    public Movie(Long movieId) {
        super(movieId);
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void deleteTicket(Long clientId) {
        Ticket ticketToBeDeleted = this.tickets.stream()
                .filter(t ->
                        t.getClient().getId().equals(clientId))
                .collect(Collectors.toList())
                .get(0);

        this.tickets.remove(ticketToBeDeleted);
    }

    public void updateTicket(Ticket ticket) {
        Ticket addedTicket = this.tickets.stream()
                .filter(t ->
                    t.getClient().getId().equals(ticket.getClient().getId()))
                .collect(Collectors.toList())
                .get(0);
        addedTicket.setPrice(ticket.getPrice());
    }

    public Set<Ticket> getTickets() {
        tickets = this.tickets == null ? new HashSet<>() : this.tickets;
        return tickets;
    }
}

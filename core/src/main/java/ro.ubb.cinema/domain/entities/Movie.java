package ro.ubb.cinema.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for the Movie entity
 *
 * @author camelia-lazar
 */

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
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
}

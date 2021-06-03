package ro.ubb.cinema.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class for the Movie entity
 *
 * @author camelia-lazar
 */

@Entity(name = "Ticket")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "ticket")
public class Ticket extends BaseEntity<Long> {
    @Column(name = "price")
    private Double price;


    @Column(name = "movieid")
    private Long movieId;

    @Column(name = "clientid")
    private Long clientId;
}

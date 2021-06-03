package ro.ubb.cinema.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class for the Cinema entity
 *
 * @author razvan-kokovics
 */

@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "cinemaGraphDirect",
                attributeNodes = {
                        @NamedAttributeNode("name"),
                        @NamedAttributeNode("address")
                }
        ),
})
@Entity(name = "Cinema")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "cinema")
public class Cinema extends BaseEntity<Long> {
    @Column(name="name")
    private String name;
    @Column(name="address")
    private String address;

    public Cinema(Long cinemaId) {
        super(cinemaId);
    }
}

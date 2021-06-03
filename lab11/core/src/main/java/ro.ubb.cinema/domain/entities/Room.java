package ro.ubb.cinema.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author fivia.
 *
 */
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "roomGraphDirect",
                attributeNodes = {
                        @NamedAttributeNode("floorNumber"),
                        @NamedAttributeNode("roomName"),
                        @NamedAttributeNode("numberOfSeats"),
                }
        ),
        @NamedEntityGraph(
                name = "roomGraphLevel1",
                attributeNodes = {
                        @NamedAttributeNode("floorNumber"),
                        @NamedAttributeNode("roomName"),
                        @NamedAttributeNode("numberOfSeats"),
                        @NamedAttributeNode(
                                value = "cinema",
                                subgraph = "cinemaSubGraphDirect"
                        )
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "cinemaSubGraphDirect",
                                attributeNodes = {
                                        @NamedAttributeNode("name"),
                                        @NamedAttributeNode("address")
                                }
                        )
                }
        )
})
@Entity(name = "Room")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"cinema"})
@ToString(callSuper = true, exclude = {"cinema"})
@Table(name = "room")
public class Room extends BaseEntity<Long>{
    @Column(name = "floorNumber")
    private Integer floorNumber;
    @Column(name = "name")
    private String roomName;
    @Column(name = "numberOfSeats")
    private Integer numberOfSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cinemaId")
    private Cinema cinema;

    public Room(Long roomId) {
        super(roomId);
    }
}

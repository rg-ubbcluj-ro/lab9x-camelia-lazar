package ro.ubb.cinema.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonSerialize
public class TicketDto extends BaseDto{
    private Long id;
    private Double price;
    private Long movieId;
    private Long clientId;
}

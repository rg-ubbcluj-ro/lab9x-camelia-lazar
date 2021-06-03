package ro.ubb.cinema.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class TrailerDto {
    Integer publishingYear;
    String soundtrack;
}

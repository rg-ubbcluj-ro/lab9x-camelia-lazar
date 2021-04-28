package ro.ubb.cinema.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CinemasDto {
    private List<CinemaDto> cinemas;
}

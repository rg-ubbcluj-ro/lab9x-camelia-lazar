package ro.ubb.cinema.converter;

import org.springframework.stereotype.Component;
import ro.ubb.cinema.domain.entities.Movie;
import ro.ubb.cinema.dto.MovieDto;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto>{

    private final TrailerConverter trailerConverter = new TrailerConverter();

    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        var model = new Movie();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setDuration(dto.getDuration());
        model.setGenre(dto.getGenre());
        model.setTrailer(trailerConverter.convertDtoToModel(dto.getTrailer()));
        return model;
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        MovieDto dto = new MovieDto(movie.getName(), movie.getDuration(), movie.getGenre(),
                trailerConverter.convertModelToDto(movie.getTrailer()));
        dto.setId(movie.getId());
        return dto;
    }
}

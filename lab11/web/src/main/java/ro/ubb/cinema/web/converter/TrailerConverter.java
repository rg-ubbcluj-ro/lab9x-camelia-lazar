package ro.ubb.cinema.web.converter;

import ro.ubb.cinema.domain.entities.Trailer;
import ro.ubb.cinema.web.dto.TicketDto;
import ro.ubb.cinema.web.dto.TrailerDto;

public class TrailerConverter {
    public Trailer convertDtoToModel(TrailerDto dto) {
        var model = new Trailer();
        model.setSoundtrack(dto.getSoundtrack());
        model.setPublishingYear(dto.getPublishingYear());

        return model;
    }

    public TrailerDto convertModelToDto(Trailer trailer) {
        if (trailer == null)
            return new TrailerDto();
        TrailerDto dto = new TrailerDto(trailer.getPublishingYear(), trailer.getSoundtrack());

        return dto;
    }
}

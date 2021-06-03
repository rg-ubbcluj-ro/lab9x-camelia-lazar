package ro.ubb.cinema.converter;

import org.springframework.stereotype.Component;
import ro.ubb.cinema.domain.entities.Ticket;
import ro.ubb.cinema.dto.TicketDto;

@Component
public class TicketConverter extends BaseConverter<Ticket, TicketDto>{


    @Override
    public Ticket convertDtoToModel(TicketDto dto) {
        var model = new Ticket();
        model.setId(dto.getId());
        model.setPrice(dto.getPrice());
        model.setMovieId(dto.getMovieId());
        model.setClientId(dto.getClientId());
        return model;
    }

    @Override
    public TicketDto convertModelToDto(Ticket ticket) {
        TicketDto dto = new TicketDto(ticket.getId(), ticket.getPrice(), ticket.getMovieId(), ticket.getClientId());
        dto.setId(ticket.getId());
        return dto;
    }
}

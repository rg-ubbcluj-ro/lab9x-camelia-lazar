package ro.ubb.cinema.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.cinema.domain.entities.Cinema;
import ro.ubb.cinema.domain.entities.Ticket;
import ro.ubb.cinema.service.ClientService;
import ro.ubb.cinema.service.MovieService;
import ro.ubb.cinema.web.controller.ClientController;
import ro.ubb.cinema.web.controller.MovieController;
import ro.ubb.cinema.web.dto.CinemaDto;
import ro.ubb.cinema.web.dto.ClientDto;
import ro.ubb.cinema.web.dto.MovieDto;
import ro.ubb.cinema.web.dto.TicketDto;

@Component
public class TicketConverter extends BaseConverter<Ticket, TicketDto>{
    @Autowired
    private ClientService clientService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

    @Autowired
    private ClientConverter clientConverter;

    @Override
    public Ticket convertDtoToModel(TicketDto dto) {
        var model = new Ticket();
        model.setId(dto.getId());
        model.setClient(clientService.getClientById(dto.getClient().getId()));
        model.setMovie(movieService.getMovieById(dto.getMovie().getId()));
        model.setPrice(dto.getPrice());
        return model;
    }

    @Override
    public TicketDto convertModelToDto(Ticket ticket) {
        MovieDto movieDto = movieConverter.convertModelToDto(ticket.getMovie());
        ClientDto clientDto = clientConverter.convertModelToDto(ticket.getClient());
        TicketDto dto = new TicketDto(ticket.getPrice(),  movieDto, clientDto);
        dto.setId(ticket.getId());
        return dto;
    }
}

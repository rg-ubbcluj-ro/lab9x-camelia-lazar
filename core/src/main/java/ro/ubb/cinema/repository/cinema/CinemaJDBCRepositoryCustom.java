package ro.ubb.cinema.repository.cinema;

import ro.ubb.cinema.domain.entities.Cinema;

import java.util.List;

public interface CinemaJDBCRepositoryCustom {
    List<Cinema> customFindAllLevel1();
    Cinema customFindOneLevel1(long id);
}

package ro.ubb.cinema.repository.client;

import ro.ubb.cinema.domain.entities.Client;

import java.util.List;

public interface ClientJDBCRepositoryCustom {
    List<Client> customFindAllLevel1();
    Client customFindOneLevel1(long id);
}

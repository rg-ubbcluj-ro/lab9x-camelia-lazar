package ro.ubb.cinema.repository.room;

import ro.ubb.cinema.domain.entities.Room;

import java.util.List;

public interface RoomJDBCRepositoryCustom {
    List<Room> customFindAllLevel1();
    Room customFindOneLevel1(long id);
}

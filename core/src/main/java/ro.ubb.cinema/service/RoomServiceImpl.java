package ro.ubb.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.cinema.domain.entities.Room;
import ro.ubb.cinema.domain.validators.RoomValidator;
import ro.ubb.cinema.domain.validators.exceptions.ValidatorException;
import ro.ubb.cinema.repository.room.RoomJDBCRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author fivia.
 */

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomJDBCRepository repository;

    private final RoomValidator roomValidator = new RoomValidator();
    private static final Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);


    @Override
    public Room saveRoom(Room room) throws ValidatorException {
        log.trace("addRoom - method entered: room={}", room);
        roomValidator.validate(room);
        Room updatedRoom = repository.save(room);
        log.trace("addRoom - method finished");
        return updatedRoom;
    }

    @Override
    public void deleteRoom(Long id){
        log.trace("deleteRoom - method entered: room={}", id);
        repository.deleteById(id);
        log.trace("deleteRoom - method finished");
    }

    @Override
    @Transactional
    public Room updateRoom(Room room) throws ValidatorException {
        log.trace("updateRoom - method entered: room={}", room);
        roomValidator.validate(room);
        Room updateRoom = repository.customFindOneLevel1(room.getId());
        updateRoom.setRoomName(room.getRoomName());
        updateRoom.setCinema(room.getCinema());
        updateRoom.setNumberOfSeats(room.getNumberOfSeats());
        updateRoom.setFloorNumber(room.getFloorNumber());
        log.trace("updateRoom - method finished");
        return room;

    }

    @Override
    public List<Room> getAllRooms()
    {
        log.trace("getAllRooms - method entered");
        List<Room> rooms = repository.findAllDirect();

        log.trace("getAllRooms - method finished: rooms={}", rooms);
        return rooms;
    }


    @Override
    public List<Room> filterRoomByNoOfSeats(Integer noToCompareWith) //>= than given number
    {
        log.trace("filterRoomByNoOfSeats - method entered: noToCompareWith={}", noToCompareWith);

        List<Room> filteredRooms = repository.getAllByNumberOfSeatsGreaterThanEqual(noToCompareWith);

        log.trace("filterRoomByNoOfSeats - method finished: filteredRooms={}", filteredRooms);
        return filteredRooms;
    }
}

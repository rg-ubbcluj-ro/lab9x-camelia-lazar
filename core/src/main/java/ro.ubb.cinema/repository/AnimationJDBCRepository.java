package ro.ubb.cinema.repository;

import ro.ubb.cinema.domain.entities.Animation;
import ro.ubb.cinema.domain.entities.Movie;

import java.util.List;

public interface AnimationJDBCRepository extends Repository<Long, Animation> {
    List<Animation> getAllByNameContaining(String name);
    List<Animation> getAllByGenreContaining(String genre);


}

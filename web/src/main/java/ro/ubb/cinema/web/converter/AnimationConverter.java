package ro.ubb.cinema.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.cinema.domain.entities.Animation;
import ro.ubb.cinema.domain.entities.Movie;
import ro.ubb.cinema.service.AnimationService;
import ro.ubb.cinema.web.dto.AnimationDto;
import ro.ubb.cinema.web.dto.MovieDto;

@Component
public class AnimationConverter extends BaseConverter<Animation, AnimationDto>{
    @Override
    public Animation convertDtoToModel(AnimationDto dto) {
        var model = new Animation();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setDuration(dto.getDuration());
        model.setGenre(dto.getGenre());
        return model;
    }

    @Override
    public AnimationDto convertModelToDto(Animation animation) {
        AnimationDto dto = new AnimationDto(animation.getName(), animation.getDuration(), animation.getGenre());
        dto.setId(animation.getId());
        return dto;
    }
}

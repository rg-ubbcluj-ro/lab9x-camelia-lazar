package ro.ubb.cinema.converter;


import ro.ubb.cinema.domain.entities.BaseEntity;
import ro.ubb.cinema.dto.BaseDto;

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

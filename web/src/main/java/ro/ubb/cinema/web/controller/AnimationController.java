package ro.ubb.cinema.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.cinema.service.AnimationService;
import ro.ubb.cinema.service.MovieService;
import ro.ubb.cinema.web.converter.AnimationConverter;
import ro.ubb.cinema.web.converter.MovieConverter;
import ro.ubb.cinema.web.dto.AnimationDto;
import ro.ubb.cinema.web.dto.AnimationsDto;
import ro.ubb.cinema.web.dto.MovieDto;
import ro.ubb.cinema.web.dto.MoviesDto;

@RestController
public class AnimationController {
    private static final Logger log = LoggerFactory.getLogger(AnimationController.class);

    @Autowired
    private AnimationService animationService;

    @Autowired
    private AnimationConverter animationConverter;

    @RequestMapping(value = "/animations")
    AnimationsDto getAllAnimations() {
        log.trace("getAllAnimations - method entered");

        AnimationsDto animationsDto = new AnimationsDto(
                animationConverter.convertModelsToDtos(
                        animationService.getAllAnimations()));

        log.trace("getAllAnimations - method finished: moviesDto={}", animationsDto);

        return animationsDto;
    }

    @RequestMapping(value = "/animations", method = RequestMethod.POST)
    AnimationDto addAnimation(@RequestBody AnimationDto animationDto){
        log.trace("addAnimation - method entered: movieDto={}", animationDto);
        var animation = animationConverter.convertDtoToModel(animationDto);

        var result = animationService.saveAnimation(animation);

        var resultModel = animationConverter.convertModelToDto(result);

        log.trace("addAnimation - method finished: resultModel={}", resultModel);
        return resultModel;
    }

    @RequestMapping(value = "/animations/{id}", method = RequestMethod.PUT)
    AnimationDto updateAnimation(@PathVariable Long id,
                         @RequestBody AnimationDto dto) {
        log.trace("updateAnimation - method entered: movieDto={}", dto);

        AnimationDto animationDto = animationConverter.convertModelToDto(
                animationService.updateAnimation(
                        animationConverter.convertDtoToModel(dto)
                ));

        log.trace("updateAnimation - method finished: movieDto={}", animationDto);

        return animationDto;
    }

    @RequestMapping(value = "/animation/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteAnimation(@PathVariable Long id) {
        log.trace("deleteAnimation - method entered: id={}", id);
        animationService.deleteAnimation(id);
        log.trace("deleteAnimation - method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/animation/filterByName", method = RequestMethod.POST)
    AnimationsDto filterAnimationByName(@RequestBody String name) {
        log.trace("filterAnimationByName - method entered: name={}", name);

        AnimationsDto animationsDto = new AnimationsDto(
                animationConverter.convertModelsToDtos(
                        animationService.filterAnimationsByName(name)));

        log.trace("filterAnimationByName - method finished");
        return animationsDto;
    }

    @RequestMapping(value = "/animations/filterByGenre", method = RequestMethod.POST)
    AnimationsDto filterAnimationByGenre(@RequestBody String genre) {
        log.trace("filterAnimationByGenre - method entered: genre={}", genre);

        AnimationsDto animationsDto = new AnimationsDto(
                animationConverter.convertModelsToDtos(
                        animationService.filterAnimationsByGenre(genre)));

        log.trace("filterAnimationByGenre - method finished");
        return animationsDto;
    }

}

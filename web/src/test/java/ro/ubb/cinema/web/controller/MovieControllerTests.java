package ro.ubb.cinema.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.ubb.cinema.domain.entities.Client;
import ro.ubb.cinema.domain.entities.Movie;
import ro.ubb.cinema.service.ClientService;
import ro.ubb.cinema.service.MovieService;
import ro.ubb.cinema.web.converter.ClientConverter;
import ro.ubb.cinema.web.converter.MovieConverter;
import ro.ubb.cinema.web.dto.ClientDto;
import ro.ubb.cinema.web.dto.MovieDto;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTests {
    private MockMvc mockMvc;

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    @Mock
    private MovieConverter movieConverter;

    private Movie movie;
    private MovieDto movieDto;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(movieController)
                .build();
    }

    @Test
    public void getMovies() throws Exception {
        List<Movie> movies = Arrays.asList(movie);
        List<MovieDto> movieDtos = Arrays.asList(movieDto);
        when(movieService.getAllMovies()).thenReturn(movies);
        when(movieConverter.convertModelsToDtos(movies)).thenReturn(movieDtos);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(movieService, times(1)).getAllMovies();
        verify(movieConverter, times(1)).convertModelsToDtos(movies);
    }
}

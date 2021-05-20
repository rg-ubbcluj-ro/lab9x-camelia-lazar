package ro.ubb.cinema.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import ro.ubb.cinema.domain.entities.Cinema;
import ro.ubb.cinema.service.CinemaService;
import ro.ubb.cinema.web.converter.CinemaConverter;
import ro.ubb.cinema.web.dto.CinemaDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CinemaControllerTests {
    private MockMvc mockMvc;

    @InjectMocks
    private CinemaController cinemaController;

    @Mock
    private CinemaService cinemaService;

    @Mock
    private CinemaConverter cinemaConverter;

    private Cinema cinema;
    private CinemaDto cinemaDto;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(cinemaController)
                .build();
    }

    @Test
    public void getCinemas() throws Exception {
        List<Cinema> cinemas = Arrays.asList(cinema);
        List<CinemaDto> cinemaDtos = Arrays.asList(cinemaDto);
        when(cinemaService.getAllCinemas()).thenReturn(cinemas);
        when(cinemaConverter.convertModelsToDtos(cinemas)).thenReturn(cinemaDtos);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/cinemas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(cinemaService, times(1)).getAllCinemas();
        verify(cinemaConverter, times(1)).convertModelsToDtos(cinemas);
    }
}

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
import ro.ubb.cinema.domain.entities.Room;
import ro.ubb.cinema.service.RoomService;
import ro.ubb.cinema.web.converter.RoomConverter;
import ro.ubb.cinema.web.dto.RoomDto;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RoomControllerTests {
    private MockMvc mockMvc;

    @InjectMocks
    private RoomController roomController;

    @Mock
    private RoomService roomService;

    @Mock
    private RoomConverter roomConverter;

    private Room room;
    private RoomDto roomDto;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(roomController)
                .build();
    }

    @Test
    public void getRooms() throws Exception {
        List<Room> rooms = Arrays.asList(room);
        List<RoomDto> roomDtos = Arrays.asList(roomDto);
        when(roomService.getAllRooms()).thenReturn(rooms);
        when(roomConverter.convertModelsToDtos(rooms)).thenReturn(roomDtos);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/rooms"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(roomService, times(1)).getAllRooms();
        verify(roomConverter, times(1)).convertModelsToDtos(rooms);
    }
}

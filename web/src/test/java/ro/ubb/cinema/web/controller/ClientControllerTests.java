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
import ro.ubb.cinema.service.ClientService;
import ro.ubb.cinema.web.converter.ClientConverter;
import ro.ubb.cinema.web.dto.ClientDto;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTests {
    private MockMvc mockMvc;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @Mock
    private ClientConverter clientConverter;

    private Client client;
    private ClientDto clientDto;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(clientController)
                .build();
    }

    @Test
    public void getClients() throws Exception {
        List<Client> clients = Arrays.asList(client);
        List<ClientDto> clientDTOs = Arrays.asList(clientDto);
        when(clientService.getAllClients()).thenReturn(clients);
        when(clientConverter.convertModelsToDtos(clients)).thenReturn(clientDTOs);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/clients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(clientService, times(1)).getAllClients();
        verify(clientConverter, times(1)).convertModelsToDtos(clients);
    }
}


package com.devsu.person.client;

import com.devsu.person.entity.Client;
import com.devsu.person.entity.dto.ClientDto;
import com.devsu.person.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        try (AutoCloseable closeable = MockitoAnnotations.openMocks(this)) {
            System.out.println(closeable.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void createClient() throws Exception {
        ClientDto clientDto = new ClientDto();

        when(clientService.createClient(any(Client.class))).thenReturn(clientDto);

        mockMvc.perform(post("/test/v1/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\"}"))
                .andExpect(status().isOk());

        verify(clientService, times(1)).createClient(any(Client.class));
    }

    @Test
    void getClients() throws Exception {
        List<ClientDto> clients = Arrays.asList(new ClientDto(), new ClientDto());

        when(clientService.getClients()).thenReturn(clients);

        mockMvc.perform(get("/test/v1/clients"))
                .andExpect(status().isOk());

        verify(clientService, times(1)).getClients();
    }

    @Test
    void getClient() throws Exception {
        ClientDto clientDto = new ClientDto();

        when(clientService.getClient(anyString())).thenReturn(clientDto);

        mockMvc.perform(get("/test/v1/clients/{clientId}", "1"))
                .andExpect(status().isOk());

        verify(clientService, times(1)).getClient("1");
    }

    @Test
    void updateClient() throws Exception {
        ClientDto clientDto = new ClientDto();

        when(clientService.updateClient(any(ClientDto.class))).thenReturn(clientDto);

        mockMvc.perform(put("/test/v1/clients/{clientId}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe Updated\"}"))
                .andExpect(status().isOk());

        verify(clientService, times(1)).updateClient(any(ClientDto.class));
    }

    @Test
    void deleteClient() throws Exception {
        ClientDto clientDto = new ClientDto();

        when(clientService.deleteClient(anyString())).thenReturn(clientDto);

        mockMvc.perform(delete("/test/v1/clients/{clientId}", "1"))
                .andExpect(status().isOk());

        verify(clientService, times(1)).deleteClient("1");
    }
}

package com.devsu.person.service.impl;

import com.devsu.person.entity.Client;
import com.devsu.person.entity.dto.ClientDto;
import com.devsu.person.repository.ClientRepository;
import com.devsu.person.testHelper.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        try (AutoCloseable closeable = MockitoAnnotations.openMocks(this)) {
            System.out.println(closeable.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCreateNewClient_andReturnDto() {
        // given
        Client client = TestHelper.createClientInstance();
        when(clientRepository.save(client)).thenReturn(client);

        // when
        ClientDto response = clientService.createClient(client);

        // then
        assertNotNull(response);
        assertEquals(client.getId(), response.getId());
        assertEquals(client.getClientId(), response.getClientId());
        assertEquals(client.getStatus(), response.getStatus());

        verify(clientRepository).save(client);
    }

    @Test
    void shouldReturnAllClients() {
        // given
        Client client1 = TestHelper.createClientInstance();
        Client client2 = TestHelper.createClientInstance();
        when(clientRepository.findAll()).thenReturn(List.of(client1, client2));

        // when
        List<ClientDto> clients = clientService.getClients();

        // then
        assertNotNull(clients);
        assertEquals(2, clients.size());

        verify(clientRepository).findAll();
    }

    @Test
    void shouldReturnClientById() {
        // given
        String clientId = "id1";
        Client client = TestHelper.createClientInstance();
        when(clientRepository.findByClientId(clientId)).thenReturn(client);

        // when
        ClientDto clientDto = clientService.getClient(clientId);

        // then
        assertNotNull(clientDto);
        assertEquals(clientId, clientDto.getClientId());

        verify(clientRepository).findByClientId(clientId);
    }

    @Test
    void shouldUpdateClient_andReturnClientDto() {
        // given
        Client client = TestHelper.createClientInstance();
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(client.getClientId());
        clientDto.setPassword("newPassword");
        clientDto.setStatus("newStatus");
        when(clientRepository.findByClientId(client.getClientId())).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);

        // when
        ClientDto updatedClient = clientService.updateClient(clientDto);

        // then
        assertNotNull(updatedClient);
        assertEquals(clientDto.getPassword(), updatedClient.getPassword());
        assertEquals(clientDto.getStatus(), updatedClient.getStatus());

        verify(clientRepository).findByClientId("id1");
        verify(clientRepository).save(client);
    }

    @Test
    void shouldDeleteClient_andReturnClientDto() {
        // given
        String clientId = "id1";
        Client client = TestHelper.createClientInstance();
        when(clientRepository.findByClientId(clientId)).thenReturn(client);

        // when
        ClientDto deletedClient = clientService.deleteClient(clientId);

        // then
        assertNotNull(deletedClient);

        verify(clientRepository).delete(client);
    }
}

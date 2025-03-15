package com.devsu.person.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.devsu.person.entity.dto.ClientDto;
import com.devsu.person.entity.dto.ClientRecord;
import com.devsu.person.service.ClientService;
import com.devsu.person.testHelper.TestHelper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class ClientControllerTest {

  @Mock private ClientService clientService;

  @InjectMocks private ClientController clientController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createClient_shouldReturnOk() {
    ClientRecord clientRecord = TestHelper.createClientRecord();
    doNothing().when(clientService).createClient(clientRecord);

    ResponseEntity<Void> response = clientController.createClient(clientRecord);

    assertEquals(200, response.getStatusCode().value());
    verify(clientService).createClient(clientRecord);
  }

  @Test
  void getClients_shouldReturnClientList() {
    List<ClientDto> clientList =
        List.of(TestHelper.createClientDto(), TestHelper.createClientDto());
    when(clientService.getClients()).thenReturn(clientList);

    ResponseEntity<List<ClientDto>> response = clientController.getClients();

    assertEquals(200, response.getStatusCode().value());
    assertEquals(clientList, response.getBody());
    verify(clientService).getClients();
  }

  @Test
  void getClient_shouldReturnClientDto() {
    String personIdentification = "12345";
    ClientDto clientDto = TestHelper.createClientDto();
    when(clientService.getClient(personIdentification)).thenReturn(clientDto);

    ResponseEntity<ClientDto> response = clientController.getClient(personIdentification);

    assertEquals(200, response.getStatusCode().value());
    assertEquals(clientDto, response.getBody());
    verify(clientService).getClient(personIdentification);
  }

  @Test
  void updateClient_shouldReturnOk() {
    String clientId = "12345";
    ClientRecord clientRecord = TestHelper.createClientRecord();
    doNothing().when(clientService).updateClient(clientId, clientRecord);

    ResponseEntity<Void> response = clientController.updateClient(clientId, clientRecord);

    assertEquals(200, response.getStatusCode().value());
    verify(clientService).updateClient(clientId, clientRecord);
  }

  @Test
  void deleteClient_shouldReturnOk() {
    String personIdentification = "12345";
    doNothing().when(clientService).deleteClient(personIdentification);

    ResponseEntity<Void> response = clientController.deleteClient(personIdentification);

    assertEquals(200, response.getStatusCode().value());
    verify(clientService).deleteClient(personIdentification);
  }
}

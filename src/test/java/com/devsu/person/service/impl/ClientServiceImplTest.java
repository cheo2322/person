package com.devsu.person.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.devsu.person.entity.Client;
import com.devsu.person.entity.Person;
import com.devsu.person.entity.dto.ClientDto;
import com.devsu.person.entity.dto.ClientRecord;
import com.devsu.person.handler.exception.DuplicateIdentificationException;
import com.devsu.person.repository.ClientRepository;
import com.devsu.person.repository.PersonRepository;
import com.devsu.person.testHelper.TestHelper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ClientServiceImplTest {

  @Mock private ClientRepository clientRepository;

  @Mock PersonRepository personRepository;

  @InjectMocks private ClientServiceImpl clientService;

  @BeforeEach
  void setUp() {
    try (AutoCloseable closeable = MockitoAnnotations.openMocks(this)) {
      System.out.println(closeable.toString());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void shouldCreateNewClient_whenPersonEntityDoesNotExist() {
    // given
    ClientRecord clientRecord = TestHelper.createClientRecord();
    Client client = TestHelper.createClientInstance();
    Person person = TestHelper.createPersonInstance();

    when(clientRepository.findByPersonIdentification(anyString())).thenReturn(Optional.empty());
    when(personRepository.findByIdentification(anyString())).thenReturn(Optional.empty());
    when(personRepository.save(any(Person.class))).thenReturn(person);
    when(clientRepository.save(any(Client.class))).thenReturn(client);

    // when
    clientService.createClient(clientRecord);

    // then
    verify(clientRepository).findByPersonIdentification(anyString());
    verify(personRepository).findByIdentification(anyString());
    verify(personRepository).save(any(Person.class));
    verify(clientRepository).save(any(Client.class));
  }

  @Test
  void shouldCreateNewClient_whenPersonEntityDoesExist() {
    // given
    ClientRecord clientRecord = TestHelper.createClientRecord();
    Client client = TestHelper.createClientInstance();
    Person person = TestHelper.createPersonInstance();

    when(clientRepository.findByPersonIdentification(anyString())).thenReturn(Optional.empty());
    when(personRepository.findByIdentification(anyString())).thenReturn(Optional.of(person));
    when(clientRepository.save(any(Client.class))).thenReturn(client);

    // when
    clientService.createClient(clientRecord);

    // then
    verify(clientRepository).findByPersonIdentification(anyString());
    verify(personRepository).findByIdentification(anyString());
    verify(clientRepository).save(any(Client.class));
  }

  @Test
  void shouldThrowDuplicateIdentificationException() {
    // given
    ClientRecord clientRecord = TestHelper.createClientRecord();
    Client client = TestHelper.createClientInstance();

    when(clientRepository.findByPersonIdentification(anyString())).thenReturn(Optional.of(client));

    // when
    DuplicateIdentificationException duplicateIdentificationException =
        assertThrows(
            DuplicateIdentificationException.class, () -> clientService.createClient(clientRecord));

    // then
    assertEquals(
        "Identification already registered for a client.",
        duplicateIdentificationException.getMessage());
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
          clientDto.setStatus(true);
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

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
import com.devsu.person.handler.exception.ClientNotFoundException;
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
    Client client = TestHelper.createClientInstance();
    when(clientRepository.findByPersonIdentification(anyString())).thenReturn(Optional.of(client));

    // when
    ClientDto clientDto = clientService.getClient("12345");

    // then
    assertNotNull(clientDto);
    assertEquals("id1", clientDto.clientId());
    assertEquals("12345", clientDto.personIdentification());

    verify(clientRepository).findByPersonIdentification(anyString());
  }

  @Test
  void shouldReturnClientNotFoundException() {
    // given
    when(clientRepository.findByPersonIdentification(anyString())).thenReturn(Optional.empty());

    // when
    ClientNotFoundException clientNotFoundException =
        assertThrows(ClientNotFoundException.class, () -> clientService.getClient("12345"));

    // then
    assertEquals("Client not found.", clientNotFoundException.getMessage());

    verify(clientRepository).findByPersonIdentification(anyString());
  }
}

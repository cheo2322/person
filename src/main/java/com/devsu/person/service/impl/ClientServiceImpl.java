package com.devsu.person.service.impl;

import com.devsu.person.entity.Client;
import com.devsu.person.entity.Person;
import com.devsu.person.entity.dto.ClientDto;
import com.devsu.person.entity.dto.ClientRecord;
import com.devsu.person.handler.exception.ClientNotFoundException;
import com.devsu.person.handler.exception.DuplicateIdentificationException;
import com.devsu.person.mapper.PersonMapper;
import com.devsu.person.repository.ClientRepository;
import com.devsu.person.repository.PersonRepository;
import com.devsu.person.service.ClientService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;
  private final PersonRepository personRepository;

  public ClientServiceImpl(ClientRepository clientRepository, PersonRepository personRepository) {
    this.clientRepository = clientRepository;
    this.personRepository = personRepository;
  }

  @Override
  public void createClient(ClientRecord clientRecord) {
    Person person = PersonMapper.recordToPerson(clientRecord);
    Client client = PersonMapper.recordToClient(clientRecord);
    client.setPerson(person);

    this.verifyClient(client.getPerson().getIdentification());

    Optional<Person> personDB = personRepository.findByIdentification(person.getIdentification());
    if (personDB.isEmpty()) {
      personRepository.save(person);
    }

    clientRepository.save(client);
  }

  @Override
  public List<ClientDto> getClients() {
    return clientRepository.findAll().stream()
        .map(PersonMapper::clientToDto)
        .collect(Collectors.toList());
  }

  @Override
  public ClientDto getClient(String personIdentification) {
    return clientRepository
        .findByPersonIdentification(personIdentification)
        .map(PersonMapper::clientToDto)
        .orElseThrow(() -> new ClientNotFoundException("Client not found."));
  }

  @Override
  public ClientDto updateClient(ClientDto clientDto) {
    Client client = clientRepository.findByClientId(clientDto.getClientId());
    if (client != null) {
      client.setPassword(clientDto.getPassword());
      client.setStatus(clientDto.getStatus());
      Client updatedClient = clientRepository.save(client);
      return toDto(updatedClient);
    }
    return null;
  }

  @Override
  public ClientDto deleteClient(String clientId) {
    Client client = clientRepository.findByClientId(clientId);
    if (client != null) {
      clientRepository.delete(client);
      return toDto(client);
    }
    return null;
  }

  /**
   * Verify if a {@link Client} is part of the clients database, by using the person identification.
   *
   * @param personIdentification the identification from the {@link Person} related to the {@link
   *     Client} to be found.
   */
  private void verifyClient(String personIdentification) {
    Optional<Client> clientByPersonIdentification =
        clientRepository.findByPersonIdentification(personIdentification);

    if (clientByPersonIdentification.isPresent()) {
      throw new DuplicateIdentificationException("Identification already registered for a client.");
    }
  }
}

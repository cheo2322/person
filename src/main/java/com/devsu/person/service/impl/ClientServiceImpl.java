package com.devsu.person.service.impl;

import com.devsu.person.entity.Client;
import com.devsu.person.entity.Person;
import com.devsu.person.entity.dto.ClientDto;
import com.devsu.person.entity.dto.ClientRecord;
import com.devsu.person.handler.exception.DuplicateIdentificationException;
import com.devsu.person.handler.exception.EntityNotFoundException;
import com.devsu.person.mapper.PersonMapper;
import com.devsu.person.repository.ClientRepository;
import com.devsu.person.repository.PersonRepository;
import com.devsu.person.service.ClientService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
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
        .orElseThrow(() -> new EntityNotFoundException("Client not found."));
  }

  @Override
  public void updateClient(String clientId, ClientRecord clientRecord) {
    Optional<Client> clientByPersonIdentification =
        clientRepository.findByPersonIdentification(clientRecord.identification());
    if (clientByPersonIdentification.isEmpty()) {
      throw new EntityNotFoundException("Client not found.");
    }

    Optional<Person> personByIdentification =
        personRepository.findByIdentification(clientRecord.identification());
    if (personByIdentification.isEmpty()) {
      throw new EntityNotFoundException("Person not found.");
    }

    Client client = clientByPersonIdentification.get();
    Person person = personByIdentification.get();

    if (!StringUtils.isBlank(clientRecord.name())) {
      person.setName(clientRecord.name());
    }

    if (!StringUtils.isBlank(clientRecord.gender())) {
      person.setGender(Person.Gender.valueOf(clientRecord.gender().toUpperCase()));
    }

    if (clientRecord.age() != null) {
      person.setAge(clientRecord.age());
    }

    if (!StringUtils.isBlank(clientRecord.identification())) {
      person.setIdentification(clientRecord.identification());
    }

    if (!StringUtils.isBlank(clientRecord.address())) {
      person.setAddress(clientRecord.address());
    }

    if (!StringUtils.isBlank(clientRecord.phone())) {
      person.setPhone(clientRecord.phone());
    }

    if (!StringUtils.isBlank(clientRecord.clientId())) {
      client.setClientId(clientRecord.clientId());
    }

    if (!StringUtils.isBlank(clientRecord.password())) {
      client.setPassword(clientRecord.password());
    }

    if (clientRecord.status() != null) {
      client.setStatus(clientRecord.status());
    }

    personRepository.save(person);
    clientRepository.save(client);
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

package com.devsu.person.mapper;

import com.devsu.person.entity.Client;
import com.devsu.person.entity.Person;
import com.devsu.person.entity.dto.ClientDto;
import com.devsu.person.entity.dto.ClientRecord;

/** Map beans including entities, records and DTOs */
public class PersonMapper {

  /**
   * Get a {@link Person} from a {@link ClientRecord}.
   *
   * @param clientRecord the instance to be mapped.
   * @return A {@link Person} instance with details from the record.
   */
  public static Person recordToPerson(ClientRecord clientRecord) {
    return Person.builder()
        .name(clientRecord.name())
        .gender(Person.Gender.valueOf(clientRecord.gender()))
        .age(clientRecord.age())
        .identification(clientRecord.identification())
        .address(clientRecord.address())
        .phone(clientRecord.phone())
        .build();
  }

  /**
   * Get a {@link Client} from a {@link ClientRecord}.
   *
   * @param clientRecord the instance to be mapped.
   * @return A {@link Client} instance with details from the record.
   */
  public static Client recordToClient(ClientRecord clientRecord) {
    return Client.builder()
        .clientId(clientRecord.clientId())
        .password(clientRecord.password())
        .status(clientRecord.status())
        .build();
  }

  /**
   * Build a {@link ClientDto} from a {@Client} instance.
   *
   * @param client the instance to build the DTO.
   * @return the {@link ClientDto} built from the instance.
   */
  public static ClientDto clientToDto(Client client) {
    return new ClientDto(client.getClientId(), client.getPerson().getIdentification());
  }
}

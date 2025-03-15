package com.devsu.person.mapper;

import com.devsu.person.entity.Client;
import com.devsu.person.entity.Person;
import com.devsu.person.entity.dto.ClientRecord;

/**
 * Map beans including Entities, records and DTOs
 */
public class PersonMapper {

  /**
   * Get a {@Person} from a {@ClientRecord}.
   * @param clientRecord the instance to be mapped.
   * @return A {@Person} instance with details from the record.
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
   * Get a {@Client} from a {@ClientRecord}.
   * @param clientRecord the instance to be mapped.
   * @return A {@Client} instance with details from the record.
   */
  public static Client recordToClient(ClientRecord clientRecord) {
    return Client.builder()
        .clientId(clientRecord.clientId())
        .password(clientRecord.password())
        .status(clientRecord.status())
        .build();
  }
}

package com.devsu.person.testHelper;

import com.devsu.person.entity.Client;
import com.devsu.person.entity.Person;
import com.devsu.person.entity.dto.ClientRecord;

public class TestHelper {

  public static Client createClientInstance() {
    Client client = new Client();
    client.setClientId("id1");
    client.setStatus(true);
    client.setPerson(createPersonInstance());

    return client;
  }

  public static ClientRecord createClientRecord() {
    return new ClientRecord(
        "name",
        Person.Gender.FEMALE.toString(),
        30,
        "12345",
        "A and B",
        "+593",
        "id1",
        "pass",
        true);
  }

  public static Person createPersonInstance() {
    return Person.builder().id(0L).identification("12345").build();
  }
}

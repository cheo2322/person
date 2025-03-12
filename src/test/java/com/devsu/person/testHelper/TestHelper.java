package com.devsu.person.testHelper;

import com.devsu.person.entity.Client;

public class TestHelper {

    public static Client createClientInstance() {
        Client client = new Client();
        client.setClientId("id1");
        client.setStatus("status1");

        return client;
    }
}

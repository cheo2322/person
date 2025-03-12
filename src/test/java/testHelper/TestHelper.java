package testHelper;

import entity.Client;

public class TestHelper {

    public static Client createClientInstance() {
        Client client = new Client();
        client.setClientId("id1");
        client.setStatus("status1");

        return client;
    }
}

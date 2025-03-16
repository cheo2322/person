# Person microservice for Devsu Test

## Tasks

1. Clients creation &#x2705;
    
    Let's use the POST /clients endpoint from the `person` API
    
    Clients to add:
    ```json
    {
      "name": "Jose Lema",
      "gender": "MALE",
      "age": 30,
      "identification": "1234567890",
      "address": "Otavalo y sn principal",
      "phone": "098254785",
      "clientId": "jlema",
      "password": "1234",
      "status": "true"
    }
    ```
    ```json
    {
      "name": "Marianela Montalvo",
      "gender": "FEMALE",
      "age": 41,
      "identification": "1234567891",
      "address": "Amazonas y NNUU",
      "phone": "097548965",
      "clientId": "mmontalvo",
      "password": "5678",
      "status": "true"
    }
    ```
    ```json
    {
      "name": "Juan Osorio",
      "gender": "MALE",
      "age": 29,
      "identification": "1234567893",
      "address": "13 de junio y Equinoccial",
      "phone": "098874587",
      "clientId": "josorio",
      "password": "1245",
      "status": "true"
    }
    ```

2. Clients' accounts creation &#x2705;

    Let's use the POST /cuentas endpoint `account` API

    Accounts to add:
    ```json
    {
      "number": "478758",
      "clientIdentification": "1234567890",
      "type": "SAVES",
      "initialBalance": 2000.0,
      "status": true
    }
    ```
    ```json
    {
      "number": "225487",
      "clientIdentification": "1234567891",
      "type": "CHECKING",
      "initialBalance": 100.0,
      "status": true
    }
    ```
    ```json
    {
      "number": "495878",
      "clientIdentification": "1234567893",
      "type": "SAVES",
      "initialBalance": 0.0,
      "status": true
    }
    ```
    ```json
    {
      "number": "496825",
      "clientIdentification": "1234567891",
      "type": "SAVES",
      "initialBalance": 540.0,
      "status": true
    }
    
    ```

3. Create a checking account for Jose Lema &#x2705;
    
    By using the same endpoint above, with the next body
    ```json
    {
        "number": "585545",
        "clientIdentification": "1234567890",
        "type": "CHECKING",
        "initialBalance": 1000.0,
        "status": true
    }
    ```

4. Perform some movements &#x2705;
    
    Let's use the POST /movimientos endpoint from `account` API
    ```json
    {
        "type": "SAVES",
        "value": -575.0,
        "accountNumber": "478758"
    }
    ```
   ```json
    {
      "type": "CHECKING",
      "value": 600.0,
      "accountNumber": "225487"
    }
    ```
    ```json
    {
      "type": "SAVES",
      "value": 150.0,
      "accountNumber": "495878"
    }
    ```
    ```json
    {
      "type": "SAVES",
      "value": -540.0,
      "accountNumber": "496825"
    }
    ```

5. List movements from an user in a date range.
   With this endpoint `/test/v1/movimientos?clientIdentification=1234567890&start=2025-03-16&end=2025-03-16` got
   ```json
   [
    {
      "date": "2025-03-16",
      "type": "CHECKING",
      "value": 1000.0,
      "balance": 1000.0,
      "accountNumber": "585545"
    },
    {
      "date": "2025-03-16",
      "type": "SAVES",
      "value": 2000.0,
      "balance": 2000.0,
      "accountNumber": "478758"
    },
    {
      "date": "2025-03-16",
      "type": "SAVES",
      "value": -575.0,
      "balance": 425.0,
      "accountNumber": "478758"
    }
   ]
   ```

# Important

In case Docker init SQL file fails to create the tables, let's create it manually

- Open a terminal while the Docker container is running
```bash
$ docker exec -it postgres-db bash
```

- Start the PostgreSQL console
```bash
$ psql -U postgres -d devsu_test
```

- Create the tables manually by using the SQL queries from `BaseDatos.sql` file
- Verify the tables are correct
```sql
\dt
```
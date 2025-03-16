# Person microservice for Devsu Test

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
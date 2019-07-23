@TODO: Add date validation in endpoint.
@TODO: Change CSV lib for https://github.com/osiegmar/FastCSV

## API Documentation

An API that connects to an Event Store build with Cassandra. Enables downloading a daily CSV with all the events.

- http://localhost:8080/swagger-ui.html


## 1. Table Structure 

```sql
CREATE KEYSPACE event_store WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3} AND durable_writes='true';


CREATE TABLE events (
  day DATE,
  ts TIMESTAMP,
  aggregate_id UUID,
  aggregate_type varchar,
  event_name varchar,
  event_id UUID,
  entity_name varchar, 
  event_version INT, 
  entity_data TEXT,
  meta TEXT,
  PRIMARY KEY ((aggregate_id, ts), day)
) WITH CLUSTERING ORDER BY (day DESC);

CREATE INDEX ON events (day);
```

## 2. Queries allowed: 
 
### Fetch all data from an aggregate given a date range:

```sql
select * from events where token(aggregate_id, ts) > token(02f60e05-2a85-4c5b-8a9f-15e02a5ba856, '2019-07-15 22:00:00');
```

## 3. Exporting data:
All you really want to do is export data by `day`. That's why there's an index on this field.

Other systems such as Apache Spark can take on splitting by columns later as Apache Cassandra is not suited for filtering.

### Download all data in a day:

Because it's an indexed field you can only use the `=` for performing queries. 

```sql
select * from events where day = '2019-07-15';
```

The process will be doing this query daily. You can do it many times in a day if you want hourly or quartely information.


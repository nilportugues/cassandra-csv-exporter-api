package com.nilportugues.api.cassandra_to_csv.domain;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.stream.Stream;

@Repository
public interface EventRepository extends CassandraRepository<Event, EventPrimaryKey> {

    @Query("SELECT * FROM events WHERE day=:day")
    Stream<Event> findByEventPrimaryKey_Day(@Param("day") LocalDate day);
}

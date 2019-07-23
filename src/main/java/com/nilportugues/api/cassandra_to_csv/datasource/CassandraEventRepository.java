package com.nilportugues.api.cassandra_to_csv.datasource;

import com.nilportugues.api.cassandra_to_csv.domain.EventRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CassandraEventRepository extends EventRepository {
}

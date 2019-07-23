package com.example.cassandratocsv.datasource;

import com.example.cassandratocsv.domain.EventRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CassandraEventRepository extends EventRepository {
}

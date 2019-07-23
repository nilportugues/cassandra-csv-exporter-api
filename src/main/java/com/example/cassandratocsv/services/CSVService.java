package com.example.cassandratocsv.services;

import com.example.cassandratocsv.domain.Event;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CSVService {

  CsvSchema schema = CsvSchema.builder()
      .addColumn("ts")
      .addColumn("day")
      .addColumn("aggregate_id")
      .addColumn("aggregate_type")
      .addColumn("event_id")
      .addColumn("event_name")
      .addColumn("event_version")
      .addColumn("entity_name")
      .addColumn("entity_data")
      .addColumn("meta")
      .build();

  public String build(Stream<Event> list) {
    try {

      final List<CSVEntity> mappedData = list.map(this::map).collect(Collectors.toList());

      CsvMapper mapper = new CsvMapper();


      return mapper
          .writer(schema.withUseHeader(true))
          .writeValueAsString(mappedData);

    } catch (Throwable t) {
      t.printStackTrace();
      return "";
    }
  }

  private CSVEntity map(Event v) {
    CSVEntity csv = new CSVEntity();
    csv.ts = v.getKey().getTs().toInstant().getEpochSecond();
    csv.day = v.getKey().getDay().toString();
    csv.aggregate_id = v.getKey().getAggregateId().toString();
    csv.aggregate_type = v.getAggregateType();
    csv.event_id = v.getEventId().toString();
    csv.event_name = v.getEntityName();
    csv.event_version = v.getEventVersion();
    csv.entity_name = v.getEventName();
    csv.entity_data = v.getEntityData();
    csv.meta = v.getMeta();
    return csv;
  }

  public String build(Event event) {
    try {
      CsvMapper mapper = new CsvMapper();
      return mapper.writer(schema.withUseHeader(false)).writeValueAsString(map(event));
    } catch (Throwable e) {
      e.printStackTrace();
      return "";
    }
  }
}


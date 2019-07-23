package com.example.cassandratocsv.domain;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@PrimaryKeyClass
public class EventPrimaryKey {

  @PrimaryKeyColumn(value="ts")
  private Date ts;

  @PrimaryKeyColumn(value="day", type = PrimaryKeyType.PARTITIONED)
  private LocalDate day;

  @PrimaryKeyColumn(value="aggregate_id")
  private UUID aggregateId;

  public EventPrimaryKey(Date ts, LocalDate day, UUID aggregateId) {
    this.ts = ts;
    this.day = day;
    this.aggregateId = aggregateId;
  }

  public Date getTs() {
    return ts;
  }

  public void setTs(Date ts) {
    this.ts = ts;
  }

  public UUID getAggregateId() {
    return aggregateId;
  }

  public void setAggregateId(UUID aggregateId) {
    this.aggregateId = aggregateId;
  }

  public LocalDate getDay() {
    return day;
  }

  public void setDay(LocalDate day) {
    this.day = day;
  }
}

package com.example.cassandratocsv.domain;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.UUID;

@Table(value = "events")
public class Event {

  @PrimaryKey
  private EventPrimaryKey key;

  @Column(value="event_id")
  private UUID eventId;

  @Column(value="event_name")
  private String eventName;

  @Column(value="event_version")
  private Integer eventVersion;

  @Column(value="entity_name")
  private String entityName;

  @Column(value="entity_data")
  private String entityData;

  @Column(value="meta")
  private String meta;

  @Column(value="aggregate_type")
  private String aggregateType;

  public Event(EventPrimaryKey key,
               UUID eventId,
               String eventName,
               Integer eventVersion,
               String entityName,
               String entityData,
               String meta,
               String aggregateType) {

    this.key = key;
    this.eventId = eventId;
    this.eventName = eventName;
    this.eventVersion = eventVersion;
    this.entityName = entityName;
    this.entityData = entityData;
    this.meta = meta;
    this.aggregateType = aggregateType;
  }

  public EventPrimaryKey getKey() {
    return key;
  }

  public void setKey(EventPrimaryKey key) {
    this.key = key;
  }

  public UUID getEventId() {
    return eventId;
  }

  public void setEventId(UUID eventId) {
    this.eventId = eventId;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public Integer getEventVersion() {
    return eventVersion;
  }

  public void setEventVersion(Integer eventVersion) {
    this.eventVersion = eventVersion;
  }

  public String getEntityName() {
    return entityName;
  }

  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }

  public String getEntityData() {
    return entityData;
  }

  public void setEntityData(String entityData) {
    this.entityData = entityData;
  }

  public String getMeta() {
    return meta;
  }

  public void setMeta(String meta) {
    this.meta = meta;
  }

  public String getAggregateType() {
    return aggregateType;
  }

  public void setAggregateType(String aggregateType) {
    this.aggregateType = aggregateType;
  }
}

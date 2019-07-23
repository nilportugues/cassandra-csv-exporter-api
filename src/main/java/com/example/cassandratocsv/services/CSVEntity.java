package com.example.cassandratocsv.services;

import java.io.Serializable;

class CSVEntity implements Serializable {
  long ts;
  String day;
  String aggregate_id;
  String event_id;
  String event_name;
  Integer event_version;
  String entity_name;
  String entity_data;
  String meta;
  String aggregate_type;

  public long getTs() {
    return ts;
  }

  public void setTs(long ts) {
    this.ts = ts;
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getAggregate_id() {
    return aggregate_id;
  }

  public void setAggregate_id(String aggregate_id) {
    this.aggregate_id = aggregate_id;
  }

  public String getEvent_id() {
    return event_id;
  }

  public void setEvent_id(String event_id) {
    this.event_id = event_id;
  }

  public String getEvent_name() {
    return event_name;
  }

  public void setEvent_name(String event_name) {
    this.event_name = event_name;
  }

  public Integer getEvent_version() {
    return event_version;
  }

  public void setEvent_version(Integer event_version) {
    this.event_version = event_version;
  }

  public String getEntity_name() {
    return entity_name;
  }

  public void setEntity_name(String entity_name) {
    this.entity_name = entity_name;
  }

  public String getEntity_data() {
    return entity_data;
  }

  public void setEntity_data(String entity_data) {
    this.entity_data = entity_data;
  }

  public String getMeta() {
    return meta;
  }

  public void setMeta(String meta) {
    this.meta = meta;
  }

  public String getAggregate_type() {
    return aggregate_type;
  }

  public void setAggregate_type(String aggregate_type) {
    this.aggregate_type = aggregate_type;
  }
}

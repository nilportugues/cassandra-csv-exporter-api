package com.example.cassandratos3.config;

import com.datastax.driver.core.PlainTextAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableCassandraRepositories(basePackages = "com.example.datasource")
public class CassandraConfiguration extends AbstractCassandraConfiguration {

  @Override
  protected String getKeyspaceName() {
    return "testKeySpace";
  }
  @Override
  protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
    return Collections.singletonList(CreateKeyspaceSpecification.createKeyspace(getKeyspaceName())
        .ifNotExists()
        .with(KeyspaceOption.DURABLE_WRITES, true)
        .withSimpleReplication());
  }


  @Bean
  public CassandraClusterFactoryBean cluster() {
    CassandraClusterFactoryBean cluster = super.cluster();

    PlainTextAuthProvider authProvider = new PlainTextAuthProvider("cassandra", "cassandra");
    cluster.setAuthProvider(authProvider);
    cluster.setContactPoints("127.0.0.1");
    cluster.setPort(9042);
    return cluster;
  }
}
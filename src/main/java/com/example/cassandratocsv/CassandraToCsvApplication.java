package com.example.cassandratocsv;

import com.example.cassandratocsv.domain.Event;
import com.example.cassandratocsv.domain.EventPrimaryKey;
import com.example.cassandratocsv.domain.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example.cassandratocsv.*")
public class CassandraToCsvApplication {

	@Autowired
	private EventRepository eventRepository;

	public static void main(String[] args) {
		SpringApplication.run(CassandraToCsvApplication.class, args);
	}

	@PostConstruct
	public void doSomeInserts() {

		CompletableFuture.runAsync(() -> {
			for(int i = 0; i<10000000; i++) {
				eventRepository.save(new Event(
						new EventPrimaryKey(new Date(), LocalDate.now(), UUID.randomUUID()),
						UUID.randomUUID(),
						"user_created",
						1,
						"user",
						"{}",
						"{}",
						"user"
				));
			}
		});


	}
}

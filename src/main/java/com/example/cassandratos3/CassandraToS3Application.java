package com.example.cassandratos3;

import com.example.cassandratos3.domain.Event;
import com.example.cassandratos3.domain.EventPrimaryKey;
import com.example.cassandratos3.domain.EventRepository;
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
@ComponentScan(basePackages = "com.example.cassandratos3.*")
public class CassandraToS3Application {

	@Autowired
	private EventRepository eventRepository;

	public static void main(String[] args) {
		SpringApplication.run(CassandraToS3Application.class, args);
	}

	@PostConstruct
	public void doSomeInserts() {
/*
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
*/

	}
}

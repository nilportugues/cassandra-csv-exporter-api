package com.nilportugues.api.cassandra_to_csv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
@ComponentScan
public class CassandraToCsvApplication {

    public static void main(String[] args) {
        SpringApplication.run(CassandraToCsvApplication.class, args);
    }
/*
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
*/
}

package com.example.cassandratos3.controllers;

import com.example.cassandratos3.domain.Event;
import com.example.cassandratos3.domain.EventRepository;
import com.example.cassandratos3.services.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
public class CsvDownloaderController {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private CSVService csvService;

  @RequestMapping(value = "/download/events/{date}", method = RequestMethod.GET)
  public CompletableFuture<ResponseEntity> all(@PathVariable String date) {
    try {
      return CompletableFuture.supplyAsync(() -> {

        Stream<Event> stream = eventRepository.findByEventPrimaryKey_Day(LocalDate.parse(date));
        String csv = csvService.build(stream);
        ByteArrayResource resource = new ByteArrayResource(csv.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-disposition", "attachment;filename=events."+date+".csv");

        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.parseMediaType("text/csv"))
            .body(resource);
      });
    } catch (Throwable e) {
      return CompletableFuture.completedFuture(ResponseEntity.badRequest().build());
    }
  }
}

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
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
  public ResponseEntity<StreamingResponseBody> all(@PathVariable String date, final HttpServletResponse response) {

    HttpHeaders headers = new HttpHeaders();
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");
    headers.add("Content-disposition", "attachment;filename=events."+date+".csv");

    try {

        //String csv = csvService.build(eventStream);
        //ByteArrayResource resource = new ByteArrayResource(csv.getBytes());

        StreamingResponseBody stream = out -> {

          BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());

          eventRepository
                  .findByEventPrimaryKey_Day(LocalDate.parse(date))
                  .map(v -> csvService.build(v))
                  .forEachOrdered(v -> {
                    try {
                      bufferedOutputStream.write(v.getBytes(), 0, v.length());
                    } catch (Throwable ignored) {

                    }
                  });
                  bufferedOutputStream.close();
        };

        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.parseMediaType("text/csv"))
            .body(stream);

    } catch (Throwable e) {
      return ResponseEntity.badRequest().body(null);
    }
  }
}

package com.nilportugues.api.cassandra_to_csv.controllers;

import com.nilportugues.api.cassandra_to_csv.controllers.api.ApiError;
import com.nilportugues.api.cassandra_to_csv.controllers.api.ApiErrors;
import com.nilportugues.api.cassandra_to_csv.domain.EventRepository;
import com.nilportugues.api.cassandra_to_csv.services.CSVService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.time.LocalDate;

@RestController
@Api(tags = "Events")
public class CsvDownloaderController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CSVService csvService;

    @RequestMapping(value = "/download/events/{date}", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Void.class),
            @ApiResponse(code = 400, message = "Bad request", response = ApiErrors.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiErrors.class)
    })
    public ResponseEntity<StreamingResponseBody> getAction(
            @ApiParam("Date value in YYYY-mm-dd format")
            @PathVariable String date, final HttpServletResponse response) {

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-disposition", "attachment;filename=events." + date + ".csv");

        final StreamingResponseBody stream = out -> {
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
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
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Throwable.class)
    public ApiErrors handleValidationExceptions(Throwable ex) {
        ApiErrors errors = new ApiErrors();
        errors.add(new ApiError("400", ex.getMessage()));

        return errors;
    }
}

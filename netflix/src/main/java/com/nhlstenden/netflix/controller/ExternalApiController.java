package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.service.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/external")
public class ExternalApiController
{

    private final ExternalApiService externalApiService;

    @Autowired
    public ExternalApiController(ExternalApiService externalApiService)
    {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/movie/{title}")
    public ResponseEntity<Object> getMovieDetails(@PathVariable String title)
    {
        try
        {
            String movieDetails = externalApiService.fetchMovieDetails(title);


            if (movieDetails == null || movieDetails.isBlank())
            {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(Map.of("message", "No movie details found for the title: " + title));
            }

            return ResponseEntity.ok(Map.of(
                    "message", "Movie details retrieved successfully",
                    "data", movieDetails
            ));
        } catch (RuntimeException e)
        {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", "Failed to fetch movie details",
                            "details", e.getMessage()
                    ));
        }
    }
}

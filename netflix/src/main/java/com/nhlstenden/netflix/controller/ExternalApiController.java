package com.nhlstenden.netflix.controller;

import com.nhlstenden.netflix.service.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> getMovieDetails(@PathVariable String title)
    {
        try
        {
            String movieDetails = externalApiService.fetchMovieDetails(title);
            return ResponseEntity.ok(movieDetails);
        } catch (RuntimeException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

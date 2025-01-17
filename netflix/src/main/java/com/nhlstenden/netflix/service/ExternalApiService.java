package com.nhlstenden.netflix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService
{

    private final RestTemplate restTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExternalApiService(JdbcTemplate jdbcTemplate)
    {
        this.restTemplate = new RestTemplate(); // Direct instantiation
        this.jdbcTemplate = jdbcTemplate;
    }

    public String fetchMovieDetails(String movieTitle)
    {
        String apiUrl = "https://www.omdbapi.com/?apikey=your_api_key&t=" + movieTitle;

        try
        {
            // Send GET request to the external API
            String response = restTemplate.getForObject(apiUrl, String.class);

            // Log the API request and response to the database
            logApiRequest("OMDB API", apiUrl, response);

            return response;
        } catch (Exception e)
        {
            // Handle errors such as timeouts or invalid responses
            logApiRequest("OMDB API", apiUrl, "Error: " + e.getMessage());
            throw new RuntimeException("Failed to fetch movie details: " + e.getMessage());
        }
    }

    private void logApiRequest(String apiName, String requestPayload, String responsePayload)
    {
        String sql = "SELECT log_api_request(?, ?, ?)";
        jdbcTemplate.update(sql, apiName, requestPayload, responsePayload);
    }
}

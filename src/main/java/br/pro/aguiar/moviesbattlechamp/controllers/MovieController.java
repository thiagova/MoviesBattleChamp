package br.pro.aguiar.moviesbattlechamp.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.pro.aguiar.moviesbattlechamp.models.Movie;
import br.pro.aguiar.moviesbattlechamp.services.MovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Value("${app.api-key}")
    private String apiKey;

    @Value("${app.api-url}")
    private String apiUrl;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> read(@PathVariable String id){
        Movie movie = MovieService.getInstance(apiUrl, apiKey).findById(id);
        return ResponseEntity.ok().body(movie);
    }

    @GetMapping()
    public ResponseEntity<Movie> readByTitle(@RequestParam String title){
        Movie movie = MovieService.getInstance(apiUrl, apiKey).findByTitile(title);
        return ResponseEntity.ok().body(movie);
    }
}

package br.pro.aguiar.moviesbattlechamp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pro.aguiar.moviesbattlechamp.models.Movie;
import br.pro.aguiar.moviesbattlechamp.services.MovieService;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Value("${app.api-key}")
    private String apiKey;

    @Value("${app.api-url}")
    private String apiUrl;
    
    @GetMapping("/next_turn")
    public ResponseEntity<List<Movie>> nextTurn(){
        MovieService movieService = 
            MovieService.getInstance(apiUrl, apiKey);
        List<Movie> movies = new ArrayList<Movie>();
        while (movies.size() != 2){
            Movie movie = 
                movieService
                .findById(generateAId());
            if (verifyMovie(movie) && !movies.contains(movie)) {
                movies.add(movie);
            }
        }
        return ResponseEntity.ok().body(movies);
    }

    private boolean verifyMovie(Movie movie){
        boolean hasId = movie.getImdbID() != null;
        String rating = movie.getImdbRating();
        boolean hasRatting = rating != null && rating.compareTo("N/A") != 0;
        String votes = movie.getImdbVotes();
        boolean hasVotes = votes != null && votes.compareTo("N/A") != 0;
        return hasId && hasRatting && hasVotes;
    }

    private String generateAId(){
        long partId = new Random().nextLong(1, 2555555);
        String partIdStr = String.valueOf(partId);
        while (partIdStr.length() < 7){
            partIdStr = "0" + partIdStr;
        }
        String idStr = "tt" + partIdStr;
        return idStr;
    }
}

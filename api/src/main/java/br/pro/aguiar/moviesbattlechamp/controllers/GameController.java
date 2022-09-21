package br.pro.aguiar.moviesbattlechamp.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pro.aguiar.moviesbattlechamp.models.Movie;
import br.pro.aguiar.moviesbattlechamp.models.Turn;
import br.pro.aguiar.moviesbattlechamp.models.TurnResponse;
import br.pro.aguiar.moviesbattlechamp.models.User;
import br.pro.aguiar.moviesbattlechamp.services.MovieService;
import br.pro.aguiar.moviesbattlechamp.services.TurnService;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Value("${app.api-key}")
    private String apiKey;

    @Value("${app.api-url}")
    private String apiUrl;

    @Autowired
    TurnService turnService;

    @GetMapping("/next_turn")
    public ResponseEntity<TurnResponse> nextTurn() {
        return ResponseEntity.ok().body(getATurnResponse());
    }

    private List<Movie> getTheMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        while (movies.size() != 2) {
            Movie movie = getAMovie();
            if (verifyMovie(movie) && !movies.contains(movie)) {
                movies.add(movie);
            }
        }
        return movies;
    }

    private TurnResponse getATurnResponse(){
        List<Turn> userturns = getUserTurns();
        Turn openedTurn = getOpenedTurn(userturns);
        if (openedTurn != null) return createATurnResponse(openedTurn);
        else return createATurnResponse();
    }

    private TurnResponse createATurnResponse(){
        TurnResponse turnResponse = null;
        User user = getCurrentUser();
        List<Movie> movies = getTheMovies();
        String firstMovieId = movies.get(0).getImdbID();
        String secondMovieId = movies.get(1).getImdbID();
        String correctAnswer = null;
        if (movies.get(0).getPoints() > movies.get(1).getPoints())
            correctAnswer = firstMovieId;
        else correctAnswer = secondMovieId;

        Turn turn = new Turn(firstMovieId, secondMovieId, correctAnswer, new Date(), user);
        turn = turnService.save(turn);
        turnResponse = new TurnResponse(turn, movies);
        return turnResponse;
    }

    private TurnResponse createATurnResponse(Turn turn){
        TurnResponse turnResponse = null;
        List<Movie> movies = new ArrayList<>();
        movies.add(getTheMovie(turn.getFirstMovie()));
        movies.add(getTheMovie(turn.getSecondMovie()));
        turnResponse = new TurnResponse(turn, movies);
        return turnResponse;
    }

    private Turn getOpenedTurn(List<Turn> userturns){
        Stream<Turn> openedTurnStream = userturns.stream();
        openedTurnStream = userturns.stream().filter(turn -> turn.getUserResponse() == null);
        List<Turn> openedTurn = openedTurnStream.toList();
        if (openedTurn.isEmpty()) return null;
        else return openedTurn.get(0);
    }

    private List<Turn> getUserTurns() {
        User user = getCurrentUser();
        return turnService.findByUser(user);
    }

    private Movie getAMovie() {
        MovieService movieService = MovieService.getInstance(apiUrl, apiKey);
        Movie movie = movieService
                .findById(generateId());
        return movie;
    }

    private Movie getTheMovie(String id) {
        MovieService movieService = MovieService.getInstance(apiUrl, apiKey);
        Movie movie = movieService.findById(id);
        return movie;
    }

    private boolean verifyMovie(Movie movie) {
        boolean hasId = movie.getImdbID() != null;
        String rating = movie.getImdbRating();
        boolean hasRatting = rating != null && rating.compareTo("N/A") != 0;
        String votes = movie.getImdbVotes();
        boolean hasVotes = votes != null && votes.compareTo("N/A") != 0;
        String type = movie.getType();
        boolean isMovie = type != null && type.compareTo("movie") == 0;
        return hasId && hasRatting && hasVotes && isMovie;
    }

    private String generateId() {
        long partId = new Random().nextLong(1, 2555555);
        String partIdStr = String.valueOf(partId);
        while (partIdStr.length() < 7) {
            partIdStr = "0" + partIdStr;
        }
        String idStr = "tt" + partIdStr;
        return idStr;
    }

    private User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        return currentUser;
    }
}

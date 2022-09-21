package br.pro.aguiar.moviesbattlechamp.models;

import java.util.List;

public class TurnResponse {
    private Turn turn;
    private List<Movie> movies;
    
    public TurnResponse(Turn turn, List<Movie> movies) {
        this.turn = turn;
        this.movies = movies;
    }
    
    public Turn getTurn() {
        return turn;
    }
    public List<Movie> getMovies() {
        return movies;
    }
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    
}

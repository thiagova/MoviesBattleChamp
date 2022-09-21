package br.pro.aguiar.moviesbattlechamp.repositories;

import br.pro.aguiar.moviesbattlechamp.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieRepository {
    @GET("/")
    Call<Movie> findById(
        @Query("apikey") String apikey,
        @Query("i") String id);

    @GET("/")
    Call<Movie> findByTitle(
        @Query("apikey") String apiKey,
        @Query("t") String title);
}

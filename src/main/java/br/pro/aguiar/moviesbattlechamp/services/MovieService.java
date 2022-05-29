package br.pro.aguiar.moviesbattlechamp.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;

import br.pro.aguiar.moviesbattlechamp.models.Movie;
import br.pro.aguiar.moviesbattlechamp.repositories.MovieRepository;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieService {

    private MovieService(){}
    private static String API_KEY = null;
    private static Retrofit RETROFIT = null;
    private static MovieRepository MOVIE_REPOSITORY = null;
    private static MovieService INSTANCE = null;
    public static MovieService getInstance(String apiUrl, String apiKey){
        if (INSTANCE == null){
            RETROFIT = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
            API_KEY = apiKey;
            MOVIE_REPOSITORY = RETROFIT.create(MovieRepository.class);
            INSTANCE = new MovieService();
        }
        return INSTANCE;
    }
    public Movie findById(String id) {
        try {
            Response<Movie> response = MOVIE_REPOSITORY.findById(API_KEY, id).execute();
            return response.body();
        } catch (IOException e) {
            return null;
        }
    }
    public Movie findByTitile(String title) {
        try {
            Response<Movie> response = MOVIE_REPOSITORY.findByTitle(API_KEY, title).execute();
            return response.body();
        } catch (IOException e) {
            return null;
        }
    }
}

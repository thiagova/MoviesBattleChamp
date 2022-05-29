package br.pro.aguiar.moviesbattlechamp.models;

import com.google.gson.annotations.SerializedName;

public class Movie {
    
    @SerializedName("Title") private String title;
    @SerializedName("Year") private String year;
    @SerializedName("imdbRating") private String imdbRating; // [float]
    @SerializedName("imdbVotes") private String imdbVotes; // [int] 
    @SerializedName("imdbID") private String imdbID; 
    @SerializedName("Type") private String type;

    // String Rated
    // String Released
    // String Runtime
    // String Genre
    // String Director
    // String Writer
    // String Actors
    // String Plot
    // String Language
    // String Country
    // String Awards
    // String Poster
    // List<Rating> Ratings
    // String Metascore
    // String DVD
    // String BoxOffice
    // String Production
    // String Website
    // boolean Response

    Movie(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPoints() {
        String rating = this.getImdbRating();
        float ratingFloat = 0;
        String votes = this.getImdbVotes();
        int votesInt = 0;
        if (rating != null && rating.compareTo("N/A") != 0)
            ratingFloat = Float.parseFloat(rating);
        if (votes != null && votes.compareTo("N/A") != 0)
            votesInt = Integer.parseInt(votes.replace(",",""));
        return ratingFloat * votesInt;
    }
    
}

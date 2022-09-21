package br.pro.aguiar.moviesbattlechamp.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "turns")
public class Turn {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstMovie;
    private String secondMovie;
    private String userResponse;
    private String correctAnswer;
    private Date createdAt;

    @ManyToOne
    private User user;

    public Turn(String firstMovie, String secondMovie, String correctAnswer, Date createdAt,
            User user) {
        this.firstMovie = firstMovie;
        this.secondMovie = secondMovie;
        this.user = user;
        this.userResponse = null;
        this.correctAnswer = correctAnswer;
        this.createdAt = createdAt;
    }

    public Turn() {
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getFirstMovie() {
        return firstMovie;
    }

    public String getSecondMovie() {
        return secondMovie;
    }

    public User getUser() {
        return user;
    }

    public void setSecondMovie(String secondMovie) {
        this.secondMovie = secondMovie;
    }

    public void setFirstMovie(String firstMovie) {
        this.firstMovie = firstMovie;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(String userResponse) {
        this.userResponse = userResponse;
    }
}

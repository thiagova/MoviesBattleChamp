package br.pro.aguiar.moviebattleui.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Auth {
    @JsonProperty("access_token")
    private String accessToken;

    public Auth() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

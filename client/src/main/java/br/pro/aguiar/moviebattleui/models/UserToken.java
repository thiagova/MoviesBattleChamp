package br.pro.aguiar.moviebattleui.models;

public class UserToken {
    private Auth auth;
    private static UserToken INSTANCE = null;

    private UserToken(){}
    public static UserToken getInstance(){
        if (INSTANCE == null){
            INSTANCE = new UserToken();
        }
        return INSTANCE;
    }
    public Auth getAuth() {
        return auth;
    }
    public void setAuth(Auth auth) {
        this.auth = auth;
    }

}

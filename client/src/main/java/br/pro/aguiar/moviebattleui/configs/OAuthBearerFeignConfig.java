package br.pro.aguiar.moviebattleui.configs;

import org.springframework.context.annotation.Bean;

import br.pro.aguiar.moviebattleui.models.UserToken;
import feign.RequestInterceptor;

public class OAuthBearerFeignConfig { 

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token =  UserToken.getInstance().getAuth().getAccessToken();
            System.out.println("LOG: " + token);
            requestTemplate.header("Authorization", "Bearer " + token);
        };
    }

}
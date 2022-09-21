package br.pro.aguiar.moviebattleui.configs;

import org.springframework.context.annotation.Bean;

import feign.auth.BasicAuthRequestInterceptor;

public class OAuthFeignConfig { 

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
         return new BasicAuthRequestInterceptor("client-id-test", "client-id-secret");
    }

}
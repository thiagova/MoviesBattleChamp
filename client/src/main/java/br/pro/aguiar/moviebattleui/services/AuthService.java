package br.pro.aguiar.moviebattleui.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.pro.aguiar.moviebattleui.configs.OAuthFeignConfig;
import br.pro.aguiar.moviebattleui.models.Auth;


@FeignClient(
    url = "http://localhost:8080", 
    name = "AuthService",
    configuration = OAuthFeignConfig.class)
public interface AuthService {
    @PostMapping("/oauth/token")
    Auth login(@RequestParam MultiValueMap<String, String> params);
}

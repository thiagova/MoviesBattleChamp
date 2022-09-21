package br.pro.aguiar.moviebattleui.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.pro.aguiar.moviebattleui.configs.OAuthBearerFeignConfig;
import br.pro.aguiar.moviebattleui.models.User;


@FeignClient(
    url = "http://localhost:8080", 
    name = "UserService",
    configuration = OAuthBearerFeignConfig.class)
public interface UserService {
    @GetMapping("/api/admin/users")
    List<User> list();
}

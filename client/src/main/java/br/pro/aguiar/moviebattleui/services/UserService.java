package br.pro.aguiar.moviebattleui.services;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import br.pro.aguiar.moviebattleui.configs.OAuthBearerFeignConfig;
import br.pro.aguiar.moviebattleui.models.User;
import feign.Headers;
import feign.Param;

@FeignClient(url = "http://localhost:8080", name = "UserService",
configuration = OAuthBearerFeignConfig.class)
public interface UserService {
    @GetMapping("/api/admin/users")
    List<User> list(@RequestHeader("token") String token);
}

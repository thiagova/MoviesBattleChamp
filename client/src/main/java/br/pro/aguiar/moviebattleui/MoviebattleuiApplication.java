package br.pro.aguiar.moviebattleui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.pro.aguiar.moviebattleui.models.Auth;
import br.pro.aguiar.moviebattleui.models.User;
import br.pro.aguiar.moviebattleui.models.UserToken;
import br.pro.aguiar.moviebattleui.services.AuthService;
import br.pro.aguiar.moviebattleui.services.UserService;

@SpringBootApplication
@EnableFeignClients
public class MoviebattleuiApplication implements CommandLineRunner {

	@Autowired
	AuthService authService;
	
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(MoviebattleuiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {

			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("grant_type", "password");
			map.add("username", "user23");
			map.add("password", "password");

			Auth auth = authService.login(map);

			UserToken.getInstance().setAuth(auth);
			
			List<User> users = userService.list();

			System.out.println(users.size());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}

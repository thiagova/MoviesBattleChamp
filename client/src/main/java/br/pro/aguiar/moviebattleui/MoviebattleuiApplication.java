package br.pro.aguiar.moviebattleui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class MoviebattleuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviebattleuiApplication.class, args);
	}

}

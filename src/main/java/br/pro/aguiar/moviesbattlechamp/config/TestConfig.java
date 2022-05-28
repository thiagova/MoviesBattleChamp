package br.pro.aguiar.moviesbattlechamp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.pro.aguiar.moviesbattlechamp.models.User;
import br.pro.aguiar.moviesbattlechamp.services.UserService;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userService.save(
                new User(
                        "user 1",
                        "user1@mail.com",
                        "password",
                        "user1"));
        userService.save(
                new User(
                "user 2",
                "user2@mail.com",
                "password",
                "user2"));
    }
}

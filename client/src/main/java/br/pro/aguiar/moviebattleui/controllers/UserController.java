package br.pro.aguiar.moviebattleui.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.pro.aguiar.moviebattleui.models.Auth;
import br.pro.aguiar.moviebattleui.models.User;
import br.pro.aguiar.moviebattleui.models.UserToken;
import br.pro.aguiar.moviebattleui.services.AuthService;
import br.pro.aguiar.moviebattleui.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @GetMapping
    public String list(HttpSession session, Model model) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "password");
        map.add("username", "user2");
        map.add("password", "password");

        Auth auth = authService.login(map);
        session
            .setAttribute(
                "access_token", 
                auth.getAccessToken());

        var token = session.getAttribute("access_token").toString();
        List<User> users = userService.list(token);
        model.addAttribute("users", users);

        return "users/list";
    }

}

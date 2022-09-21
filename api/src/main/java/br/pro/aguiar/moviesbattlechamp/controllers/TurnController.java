package br.pro.aguiar.moviesbattlechamp.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pro.aguiar.moviesbattlechamp.models.Turn;
import br.pro.aguiar.moviesbattlechamp.models.User;
import br.pro.aguiar.moviesbattlechamp.services.TurnService;

@RestController
@RequestMapping("/api/turns")
public class TurnController {

    @Autowired
    private TurnService turnService;
    
    @GetMapping
    public ResponseEntity<List<Turn>> index(){
        User user = getCurrentUser();
        List<Turn> turn = turnService.findByUser(user);
        return ResponseEntity.ok().body(turn);
    } 

    @PostMapping
    public ResponseEntity<Turn> create(@RequestBody Turn turn){
        User user = getCurrentUser();
        turn.setUser(user);
        turn.setCreatedAt(new Date());
        turn = turnService.save(turn);
        return ResponseEntity.status(201).body(turn);
    }

    private User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = securityContext.getAuthentication();
        User currentUser = (User) auth.getPrincipal();
        return currentUser;
    }
}

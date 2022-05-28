package br.pro.aguiar.moviesbattlechamp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pro.aguiar.moviesbattlechamp.models.User;
import br.pro.aguiar.moviesbattlechamp.services.UserService;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<List<User>> index(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    } 

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> read(@PathVariable long id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        user = userService.save(user);
        return ResponseEntity.status(201).body(user);
    }
}

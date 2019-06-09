package app.controller;

import app.entity.User;
import app.form.UserForm;
import app.service.UserDetailsServiceImpl;
import app.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsService;


    @Autowired
    public UserController(UserService userService, UserDetailsServiceImpl userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody UserForm user) {
        userService.save(new User(user));
        return new ResponseEntity<>("User succesfully add", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity getUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(new Gson().toJson(users), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity getUser(@RequestParam String email) {
        userDetailsService.loadUserByUsername(email);
        return new ResponseEntity<>(userDetailsService.loadUserByUsername(email), HttpStatus.OK);
    }

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}


package com.todoapp.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.model.UserMapper;
import com.todoapp.service.UserService;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @RequestMapping(value = "/login", method = GET)
    @PermitAll
    ResponseEntity<Boolean> isCurrentUserLoggedIn() {
        return new ResponseEntity<>(userService.isCurrentUserLoggedIn(), OK);
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @RequestMapping(method = GET)
    ResponseEntity<UserMapper> getCurrentUser() {
        return ok(userService.getCurrentUser());
    }
}

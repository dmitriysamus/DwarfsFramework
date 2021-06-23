package com.angrydwarfs.Dwarfs_Framework.controllers;

import com.angrydwarfs.Dwarfs_Framework.models.User;
import com.angrydwarfs.Dwarfs_Framework.payload.response.MessageResponse;
import com.angrydwarfs.Dwarfs_Framework.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/users")
public class UserController {

    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test (User user) {

        return ResponseEntity
                .ok(new MessageResponse("WIN!"));

    }


}



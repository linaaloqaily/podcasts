package com.example.podcasts.Controller;


import com.example.podcasts.Model.User;
import com.example.podcasts.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;



    // Endpoint for User

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) throws IOException {
        logger.info("Get user by id");
        return ResponseEntity.status(200).body(userService.getUserById(id));
    }


    @PostMapping("/register")
    public ResponseEntity addUser(@Valid @RequestBody User user){
        userService.addUser(user);
        logger.info("Add user");
        return ResponseEntity.status(201).body("User Added");
    }


    @PutMapping ("/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user) throws IOException {
        userService.updateUser(id,user);
        logger.info("Update user");
        return ResponseEntity.status(200).body("User updated");
    }

    // Endpoint for Admin

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getUsersForAdmin(){
        logger.info("Get all users");
        return ResponseEntity.status(200).body(userService.getUsers());
    }


    // Endpoint for both

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) throws IOException {
        userService.deleteUser(id);
        logger.info("Delete user");
        return ResponseEntity.status(200).body("User Deleted");
    }

}

package com.example.podcasts.Controller;


import com.example.podcasts.Model.User;
import com.example.podcasts.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;


    // Endpoint for User

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) throws IOException {
        return ResponseEntity.status(200).body(userService.getUserById(id));
    }


    @PostMapping
    public ResponseEntity addUser(@Valid @RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.status(201).body("User Added");
    }

    @PutMapping ("/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user) throws IOException {
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body("User Updated");
    }

    // Endpoint for Admin

    @GetMapping
    public ResponseEntity<List<User>> getUsersForAdmin(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }


    // Endpoint for both

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) throws IOException {
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("User Deleted");
    }

}

package com.example.podcasts.Service;


import com.example.podcasts.Model.User;
import com.example.podcasts.Repository.UserRepository;
import com.example.podcasts.handelException.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    // User

    public Optional<User> getUserById(Integer id) throws IOException {
        User oldUser = userRepository.findById(id).orElse(null);
        if(oldUser == null) {
            throw new ApiRequestException("This user doesn't exist");
        }
      return userRepository.findById(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(Integer id ,User user) throws IOException {
        User oldUser = userRepository.findById(id).orElse(null);
        if(oldUser == null) {
            throw new ApiRequestException("This user doesn't exist");
       }
        oldUser.setId(user.getId());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());
        userRepository.save(oldUser);
    }

    // Admin

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Both user and admin

    public boolean deleteUser(Integer id) throws IOException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null){
            throw new ApiRequestException("This user doesn't exist");
        }
        userRepository.deleteById(id);
        return true;
    }

}

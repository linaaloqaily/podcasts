package com.example.podcasts.Service;

import com.example.podcasts.Model.User;
import com.example.podcasts.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {



    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository usersRepository) {
        this.userRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findUsersByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }
//        System.out.println(user.getAuthorities());
        return user;
    }
}

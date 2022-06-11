package com.example.podcasts.Repository;

import com.example.podcasts.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {


    User findUsersByUsername(String username);
}

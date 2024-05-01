package com.myBlog.repository;

import com.myBlog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByEmail(String email) ;
   User findByUsername(String username) ;
   Optional<User> findByUsernameOrEmail(String username ,String email) ;

   Boolean existsByUsername(String username);
   Boolean  existsByEmail(String email);




}

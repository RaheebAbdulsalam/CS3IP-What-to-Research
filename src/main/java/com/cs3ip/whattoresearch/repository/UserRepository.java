package com.cs3ip.whattoresearch.repository;

import com.cs3ip.whattoresearch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);


}
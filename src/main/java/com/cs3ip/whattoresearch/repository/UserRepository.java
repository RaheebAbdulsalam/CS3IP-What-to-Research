package com.cs3ip.whattoresearch.repository;

import com.cs3ip.whattoresearch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for managing User entities.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Retrieves a user by their email address.
     * @param email The email address of the user.
     * @return The User object representing the user with the email address.
     */
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

}

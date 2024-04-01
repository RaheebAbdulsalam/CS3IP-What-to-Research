package com.cs3ip.whattoresearch.repository;

import com.cs3ip.whattoresearch.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for managing Role entities.
 */
public interface RoleRepository extends JpaRepository<Role,Long> {

    /**
     * Retrieves a role by its name.
     * @param name The name of the role.
     * @return The Role object representing the role.
     */
    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    Role findByName(String name);

}

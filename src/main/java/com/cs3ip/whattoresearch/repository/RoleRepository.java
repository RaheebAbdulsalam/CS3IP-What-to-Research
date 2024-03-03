package com.cs3ip.whattoresearch.repository;

import com.cs3ip.whattoresearch.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    Role findByName(String name);

}

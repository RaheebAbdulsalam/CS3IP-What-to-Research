package com.cs3ip.whattoresearch.repository;

import com.cs3ip.whattoresearch.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Supervisor entities.
 */
@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {

}

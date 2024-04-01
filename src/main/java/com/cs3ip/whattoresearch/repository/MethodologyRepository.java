package com.cs3ip.whattoresearch.repository;

import com.cs3ip.whattoresearch.model.Methodology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing project methodologies entities.
 */
@Repository
public interface MethodologyRepository extends JpaRepository<Methodology, Integer> {}
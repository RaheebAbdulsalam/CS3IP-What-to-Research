package com.cs3ip.whattoresearch.repository;


import com.cs3ip.whattoresearch.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing project types entities.
 */
@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {}

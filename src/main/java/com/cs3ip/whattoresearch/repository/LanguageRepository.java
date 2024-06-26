package com.cs3ip.whattoresearch.repository;

import com.cs3ip.whattoresearch.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing programming languages entities.
 */
@Repository
public interface LanguageRepository extends JpaRepository <Language, Integer> {
}

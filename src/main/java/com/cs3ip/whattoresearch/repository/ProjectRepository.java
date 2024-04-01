package com.cs3ip.whattoresearch.repository;

import com.cs3ip.whattoresearch.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for managing Project entities.
 */
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    /**
     * Finds projects based on student preferences.
     * @param methodology The methodology of the project.
     * @param type The type of the project.
     * @param languages The list of preferred programming languages for the project.
     * @param languageSize The number of preferred programming languages specified.
     * @param skillLevel The programming skill level required for the project.
     * @return A list of Project objects matching the specified preferences.
     */
        @Query("SELECT p FROM Project p " +
            "JOIN p.types t " +
            "WHERE p.projectMethodology.methodology_type = :methodology " +
            "AND t.name = :type " +
            "AND (:languageSize = (SELECT COUNT(lang) FROM p.preferredLanguages lang WHERE lang.language IN :languages)) " +
            "AND p.programmingSkill.skillLevel = :skillLevel")
    List<Project> findProjectsByPreferences(@Param("methodology") String methodology,
                                            @Param("type") String type,
                                            @Param("languages") List<String> languages,
                                            @Param("languageSize") int languageSize,
                                            @Param("skillLevel") String skillLevel);

}

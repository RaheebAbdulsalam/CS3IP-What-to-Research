package com.cs3ip.whattoresearch.repository;

import com.cs3ip.whattoresearch.model.Language;
import com.cs3ip.whattoresearch.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("SELECT p FROM Project p " +
            "JOIN p.types t " +
            "WHERE (:methodology = 'No Preferences' OR p.projectMethodology.methodology_type = :methodology) " +
            "AND (:type = 'No Preferences' OR t.name = :type) " +
            "AND (:languageSize = (CASE WHEN :methodology = 'No Preferences' OR :type = 'Not Applicable' THEN 1 ELSE (SELECT COUNT(lang) FROM p.preferredLanguages lang WHERE lang.language IN :languages) END)) " +
            "AND p.programmingSkill.skillLevel = :skillLevel")
    List<Project> findProjectsByPreferences(@Param("methodology") String methodology,
                                            @Param("type") String type,
                                            @Param("languages") List<String> languages,
                                            @Param("languageSize") int languageSize,
                                            @Param("skillLevel") String skillLevel);



    //    List<Project> findByNameContainingIgnoreCase(String query);

    //    @Query("SELECT p FROM Project p " +
//            "JOIN p.types t " +
//            "WHERE p.projectMethodology.methodology_type = :methodology " +
//            "AND t.name = :type " +
//            "AND (:languageSize = (SELECT COUNT(lang) FROM p.preferredLanguages lang WHERE lang.language IN :languages)) " +
//            "AND p.programmingSkill.skillLevel = :skillLevel")
//    List<Project> findProjectsByPreferences(@Param("methodology") String methodology,
//                                            @Param("type") String type,
//                                            @Param("languages") List<String> languages,
//                                            @Param("languageSize") int languageSize,
//                                            @Param("skillLevel") String skillLevel);


//    @Query("SELECT p FROM Project p " +
//            "JOIN p.types t " +
//            "WHERE p.projectMethodology.methodology_type = :methodology " +
//            "AND t.name = :type " +
//            "AND :language IN (SELECT lang.language FROM p.preferredLanguages lang) " +
//            "AND p.programmingSkill.skillLevel = :skillLevel")
//    List<Project> findProjectsByPreferences(@Param("methodology") String methodology,
//                                            @Param("type") String type,
//                                            @Param("language") String language,
//                                            @Param("skillLevel") String skillLevel);
}

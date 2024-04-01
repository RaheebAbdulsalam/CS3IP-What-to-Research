package com.cs3ip.whattoresearch.repository;

import com.cs3ip.whattoresearch.model.ShortList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing ShortList entities.
 */
public interface ShortListRepository extends JpaRepository<ShortList, Integer> {

    /**
     * Finds all shortlisted projects for a given user ID.
     * @param userId The ID of the user.
     * @return A list of ShortList objects associated with the user ID.
     */
    List<ShortList> findByUserId(Integer userId);

    /**
     * Finds a shortlisted project by user ID and project ID.
     * @param userId The ID of the user.
     * @param projectId The ID of the project.
     * @return The ShortList object representing the shortlisted project for the user and project IDs.
     */
    ShortList findByUserIdAndProjectId(Integer userId, Integer projectId);

    /**
     * Finds a shortlisted project by user ID and shortlist project ID.
     * @param userId The ID of the user.
     * @param favouriteProjectId The ID of the shortlisted project.
     * @return The ShortList object representing the shortlisted project for the user and shortlist project ID.
     */
    ShortList findByUserIdAndId(Integer userId, Integer favouriteProjectId);
}

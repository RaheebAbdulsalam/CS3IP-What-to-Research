package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.exception.ResourceNotFoundException;
import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.model.User;
import com.cs3ip.whattoresearch.model.ShortList;
import com.cs3ip.whattoresearch.repository.ProjectRepository;
import com.cs3ip.whattoresearch.repository.ShortListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing user shortlists.
 */
@Service
public class ShortListService {

    @Autowired
    private ShortListRepository shortListRepository;

    @Autowired
    private ProjectRepository projectRepository;

    /**
     * Retrieves the favorite projects the user has added to the shortlist.
     *
     * @param user The user.
     * @return A list of ShortList objects representing the favorite projects the user has added to the shortlist.
     */
    public List<ShortList> getFavouriteProjects(User user) {
        return shortListRepository.findByUserId(user.getId());
    }

    /**
     * Retrieves a project by its ID.
     *
     * @param id The ID of the project to retrieve.
     * @return The project with the ID.
     * @throws ResourceNotFoundException If no project is found with the specified ID.
     */
    public Project getProjectById(Integer id) {
        return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }

    /**
     * Adds a project to the student's shortlist.
     *
     * @param user       The user who shortlist the project.
     * @param projectId  The ID of the project to add to the shortlist.
     */
    public void addToShortList(User user, Integer projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
        ShortList existingProject = shortListRepository.findByUserIdAndProjectId(user.getId(), projectId);
        if (existingProject != null) {
            shortListRepository.save(existingProject);
        } else {
            ShortList newProject = new ShortList(user.getId(), project);
           shortListRepository.save(newProject);
        }
    }

    /**
     * Removes a project from the user's shortlist.
     *
     * @param user                The user whose project will be removed from is shortlist.
     * @param favouriteProjectId  The ID of the project to remove from the shortlist.
     * @throws ResourceNotFoundException If the project is not found in the user's shortlist.
     */
    public void removeShortListProject(User user, Integer favouriteProjectId) {
       ShortList shortListProject = shortListRepository.findByUserIdAndId(user.getId(), favouriteProjectId);

        if (shortListProject != null) {
           shortListRepository.delete(shortListProject);
        } else {
            throw new RuntimeException("Unable to find the project");
        }
    }


}

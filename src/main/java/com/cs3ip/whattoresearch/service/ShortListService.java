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

@Service
public class ShortListService {

    @Autowired
    private ShortListRepository shortListRepository;

    @Autowired
    private ProjectRepository projectRepository;
    public List<ShortList> getFavouriteProjects(User user) {
        return shortListRepository.findByUserId(user.getId());
    }

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

    public void removeShortListProject(User user, Integer favouriteProjectId) {
       ShortList shortListProject = shortListRepository.findByUserIdAndId(user.getId(), favouriteProjectId);

        if (shortListProject != null) {
           shortListRepository.delete(shortListProject);
        } else {
            throw new RuntimeException("Project not found");
        }
    }

    public Project getProjectById(Integer id) {
        return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
    }


}

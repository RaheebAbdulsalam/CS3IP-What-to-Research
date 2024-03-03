package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.model.User;
import com.cs3ip.whattoresearch.model.WishList;
import com.cs3ip.whattoresearch.repository.ProjectRepository;
import com.cs3ip.whattoresearch.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ProjectRepository projectRepository;
    public List<WishList> getFavouriteProjects(User user) {
        return wishListRepository.findByUserId(user.getId());
    }

    public void addToWishList(User user, Integer projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
        WishList existingProject = wishListRepository.findByUserIdAndProjectId(user.getId(), projectId);
        if (existingProject != null) {
            wishListRepository.save(existingProject);
        } else {
            WishList newProject = new WishList(user.getId(), project);
           wishListRepository.save(newProject);
        }
    }

    public void removeWishListProject(User user, Integer favouriteProjectId) {
       WishList wishListProject = wishListRepository.findByUserIdAndId(user.getId(), favouriteProjectId);

        if (wishListProject != null) {
           wishListRepository.delete(wishListProject);
        } else {
            throw new RuntimeException("Project not found");
        }
    }


}

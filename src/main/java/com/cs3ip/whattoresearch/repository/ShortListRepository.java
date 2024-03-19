package com.cs3ip.whattoresearch.repository;

import com.cs3ip.whattoresearch.model.ShortList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortListRepository extends JpaRepository<ShortList, Integer> {

    List<ShortList> findByUserId(Integer userId);
    ShortList findByUserIdAndProjectId(Integer userId, Integer projectId);
    ShortList findByUserIdAndId(Integer userId, Integer favouriteProjectId);
}

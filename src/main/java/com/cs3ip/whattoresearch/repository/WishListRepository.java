package com.cs3ip.whattoresearch.repository;

import com.cs3ip.whattoresearch.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Integer> {

    List<WishList> findByUserId(Integer userId);
    WishList findByUserIdAndProjectId(Integer userId, Integer projectId);

   WishList findByUserIdAndId(Integer userId, Integer favouriteProjectId);
}

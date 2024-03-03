package com.cs3ip.whattoresearch.repository;

import com.cs3ip.whattoresearch.model.SkillLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillLevelRepository extends JpaRepository<SkillLevel, Integer> {
}

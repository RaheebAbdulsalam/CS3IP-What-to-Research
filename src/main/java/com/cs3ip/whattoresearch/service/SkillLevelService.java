package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.model.SkillLevel;
import com.cs3ip.whattoresearch.repository.SkillLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing programming skill levels.
 */
@Service
public class SkillLevelService {

    @Autowired
    private SkillLevelRepository skillLevelRepository;

    /**
     * Retrieves all programming skill levels.
     *
     * @return A list of all programming skill levels.
     */
    public List<SkillLevel> getAllLevels() {
        return skillLevelRepository.findAll();
    }


}

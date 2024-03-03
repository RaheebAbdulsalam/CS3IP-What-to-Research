package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.exception.ResourceNotFoundException;
import com.cs3ip.whattoresearch.model.SkillLevel;
import com.cs3ip.whattoresearch.repository.SkillLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillLevelService {

    @Autowired
    private SkillLevelRepository skillLevelRepository;


    public List<SkillLevel> getAllLevels() {
        return skillLevelRepository.findAll();
    }


    public SkillLevel getLevelById(Integer id) {
        return skillLevelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + id));
    }

}

package com.cs3ip.whattoresearch.service;


import com.cs3ip.whattoresearch.exception.ResourceNotFoundException;
import com.cs3ip.whattoresearch.model.Language;
import com.cs3ip.whattoresearch.model.Methodology;
import com.cs3ip.whattoresearch.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectLanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }


    public Language getProjectLanguageById(Integer id) {
        return languageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Language not found with id: " + id));
    }
}

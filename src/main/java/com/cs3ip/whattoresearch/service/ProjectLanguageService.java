package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.model.Language;
import com.cs3ip.whattoresearch.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing project programming languages.
 */
@Service
public class ProjectLanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    /**
     * Retrieves all project programming languages.
     *
     * @return A list of all project programming languages.
     */
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

}

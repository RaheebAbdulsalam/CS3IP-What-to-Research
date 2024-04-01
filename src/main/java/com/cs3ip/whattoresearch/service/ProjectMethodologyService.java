package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.exception.ResourceNotFoundException;
import com.cs3ip.whattoresearch.model.Methodology;
import com.cs3ip.whattoresearch.repository.MethodologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing project methodologies.
 */
@Service
public class ProjectMethodologyService {

    @Autowired
    private MethodologyRepository methodologyRepository;

    /**
     * Retrieves all project methodologies.
     *
     * @return A list of all project methodologies.
     */
    public List<Methodology> getAllMethodologies() {
        return methodologyRepository.findAll();
    }

}


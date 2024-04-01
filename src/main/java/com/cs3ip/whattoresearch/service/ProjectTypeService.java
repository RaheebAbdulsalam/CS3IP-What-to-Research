package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.model.Type;
import com.cs3ip.whattoresearch.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for managing project types.
 */
@Service
public class ProjectTypeService {

    @Autowired
    private TypeRepository typeRepository;

    /**
     * Retrieves all project types.
     *
     * @return A list of all project types.
     */
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

}


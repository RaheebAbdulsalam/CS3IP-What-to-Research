package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.model.Supervisor;
import com.cs3ip.whattoresearch.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing supervisors.
 */
@Service
public class SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    /**
     * Retrieves a list of all supervisors.
     * @return List of Supervisor objects.
     */
    public List<Supervisor> getAllSupervisors() {
        return supervisorRepository.findAll();
    }

    /**
     * Adds a new supervisor.
     * @param supervisor The Supervisor object to add.
     * @return The added Supervisor object.
     */
    public Supervisor addSupervisor(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    /**
     * Removes a supervisor by ID.
     * @param id The ID of the supervisor to remove.
     */
    public void removeSupervisor(Integer id) {
        supervisorRepository.deleteById(id);
    }
}

package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.exception.ResourceNotFoundException;
import com.cs3ip.whattoresearch.model.Language;
import com.cs3ip.whattoresearch.model.Project;
import com.cs3ip.whattoresearch.model.Supervisor;
import com.cs3ip.whattoresearch.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;


    public List<Supervisor> getAllSupervisors() {
        return supervisorRepository.findAll();
    }


    public Supervisor getSupervisorById(Integer id) {
        return supervisorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supervisor not found with id: " + id));
    }

    public Supervisor addSupervisor(Supervisor supervisor) {
        return supervisorRepository.save(supervisor);
    }

    public void removeSupervisor(Integer id) {
        supervisorRepository.deleteById(id);
    }
}

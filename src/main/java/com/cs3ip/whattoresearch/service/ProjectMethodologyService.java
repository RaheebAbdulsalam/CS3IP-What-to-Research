package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.exception.ResourceNotFoundException;
import com.cs3ip.whattoresearch.model.Methodology;
import com.cs3ip.whattoresearch.model.Type;
import com.cs3ip.whattoresearch.repository.MethodologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMethodologyService {

    @Autowired
    private MethodologyRepository methodologyRepository;

    public List<Methodology> getAllMethodologies() {
        return methodologyRepository.findAll();
    }


    public Methodology getProjectMethodologyById(Integer id) {
        return methodologyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Methodology not found with id: " + id));
    }

    public Methodology createProjectMethodology(Methodology methodology) {
        return methodologyRepository.save(methodology);
    }

    public Methodology updateMethodology(Integer id, Methodology methodologyDetails) {
        Methodology methodology = getProjectMethodologyById(id);
        methodology.setMethodology_type(methodologyDetails.getMethodology_type());
        return methodologyRepository.save(methodology);
    }

    public void deleteProjectMethodology(Integer id) {
        methodologyRepository.deleteById(id);
    }
}


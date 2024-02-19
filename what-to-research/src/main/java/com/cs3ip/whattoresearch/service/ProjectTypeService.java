package com.cs3ip.whattoresearch.service;


import com.cs3ip.whattoresearch.exception.ResourceNotFoundException;
import com.cs3ip.whattoresearch.model.Type;
import com.cs3ip.whattoresearch.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectTypeService {

    @Autowired
    private TypeRepository typeRepository;

    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }


    public Type getProjectTypeById(Integer id) {
        return typeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Type not found with id: " + id));
    }

    public Type createProjectType(Type type) {
        return typeRepository.save(type);
    }

    public Type updateType(Integer id, Type typeDetails) {
        Type type = getProjectTypeById(id);
       type.setName(typeDetails.getName());
        return typeRepository.save(type);
    }

    public void deleteProjectType(Integer id) {
        typeRepository.deleteById(id);
    }
}


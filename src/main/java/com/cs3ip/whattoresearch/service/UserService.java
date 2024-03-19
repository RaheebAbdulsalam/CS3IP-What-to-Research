package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.exception.ResourceNotFoundException;
import com.cs3ip.whattoresearch.model.Role;
import com.cs3ip.whattoresearch.model.User;
import com.cs3ip.whattoresearch.repository.RoleRepository;
import com.cs3ip.whattoresearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void saveWithDefaultRole(User user){

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPassword= encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        //Save a new user as a student
        Role studentRole=roleRepository.findByName("STUDENT");
        user.addRole(studentRole);
        userRepository.save(user);
    }

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User getUserId(Integer id) {
        return userRepository.findById(id).get();
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public void saveUser(User user) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPassword= encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public boolean emailExists(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
        return userOptional.isPresent();
    }

    public void updateProfile(User user) {
        userRepository.save(user);
    }


    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }
    public User updateUser(Integer id, User userDetails) {
        User user = getUserById(id);
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setStudentNumber(userDetails.getStudentNumber());
        user.setRoles(userDetails.getRoles());
        return userRepository.save(user);
    }

    public void removeUser(Integer id) {
        userRepository.deleteById(id);
    }
}

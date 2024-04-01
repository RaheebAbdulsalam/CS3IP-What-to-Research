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

/**
 * Service class for managing users.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Saves a new user with the default role of STUDENT.
     *
     * @param user The user to be saved.
     */
    public void saveWithDefaultRole(User user){
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPassword= encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role studentRole=roleRepository.findByName("STUDENT");
        user.addRole(studentRole);
        userRepository.save(user);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return A list of all users.
     */
    public List<User> listAll(){
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user.
     * @return The user with the specified ID.
     * @throws ResourceNotFoundException If no user is found with the specified ID.
     */
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }
    /**
     * Retrieves a list of all roles.
     *
     * @return A list of all roles.
     */
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    /**
     * Saves a user.
     *
     * @param user The user to be saved.
     */
    public void saveUser(User user) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPassword= encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    /**
     * Checks if a user with the email exists.
     *
     * @param email The email to check.
     * @return True if a user with the email exists.
     */
    public boolean emailExists(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
        return userOptional.isPresent();
    }

    /**
     * Updates the details of a user.
     *
     * @param id          The ID of the user to update.
     * @param userDetails The updated user details.
     * @return The updated user.
     * @throws ResourceNotFoundException If no user is found with the ID.
     */
    public User updateUser(Integer id, User userDetails) {
        User user = getUserById(id);
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setStudentNumber(userDetails.getStudentNumber());
        user.setRoles(userDetails.getRoles());
        return userRepository.save(user);
    }

    /**
     * Removes a user by ID.
     *
     * @param id The ID of the user to remove.
     */
    public void removeUser(Integer id) {
        userRepository.deleteById(id);
    }
}

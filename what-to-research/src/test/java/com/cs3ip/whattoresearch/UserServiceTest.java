package com.cs3ip.whattoresearch;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cs3ip.whattoresearch.model.Role;
import com.cs3ip.whattoresearch.model.User;
import com.cs3ip.whattoresearch.repository.RoleRepository;
import com.cs3ip.whattoresearch.repository.UserRepository;
import com.cs3ip.whattoresearch.service.UserService;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveWithDefaultRole() {
        User user = new User();
        user.setFirstName("Raheeb");
        user.setLastName("Abdulsalam");
        user.setStudentNumber("210119617");
        user.setEmail("raheeb@gmail.com");
        user.setPassword("password");
        Role role = new Role();
        role.setName("STUDENT");

        when(roleRepository.findByName("STUDENT")).thenReturn(role);
        userService.saveWithDefaultRole(user);

        verify(userRepository, times(1)).save(user);
        assertNotNull(user.getPassword());
        assertNotEquals("password", user.getPassword());
    }

    @Test
    public void testListAll() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.listAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetUser() {
        User user = new User();
        user.setId(1);
        user.setEmail("raheeb@gmail.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = userService.getUserId(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("raheeb@gmail.com", result.getEmail());
    }

    @Test
    public void testGetRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role());
        roles.add(new Role());

        when(roleRepository.findAll()).thenReturn(roles);

        List<Role> result = userService.getRoles();

        assertEquals(2, result.size());
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setEmail("raheeb@gmail.com");
        user.setPassword("password");

        userService.saveUser(user);

        verify(userRepository, times(1)).save(user);
        assertNotNull(user.getPassword());
        assertNotEquals("password", user.getPassword());
    }

    @Test
    public void testEmailExists() {
        String email = "raheeb@gmail.com";
        when(userRepository.findByEmail(email)).thenReturn(new User());

        boolean result = userService.emailExists(email);

        assertTrue(result);
    }

    @Test
    public void testEmailDoesNotExist() {
        String email = "raheeb@gmail.com";
        when(userRepository.findByEmail(email)).thenReturn(null);

        boolean result = userService.emailExists(email);

        assertFalse(result);
    }
}



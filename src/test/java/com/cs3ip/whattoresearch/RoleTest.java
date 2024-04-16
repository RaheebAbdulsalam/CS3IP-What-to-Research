package com.cs3ip.whattoresearch;

import com.cs3ip.whattoresearch.model.Role;
import com.cs3ip.whattoresearch.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = WhatToResearchApplication.class)
@Transactional
public class RoleTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void testCreateRoles() {
        Role student = new Role("STUDENT");
        Role admin = new Role("ADMIN");

        roleRepository.saveAll(List.of(student, admin));

        List<Role> listRoles = roleRepository.findAll();
        assertThat(listRoles.size()).isEqualTo(4);
    }

}
package com.cs3ip.whattoresearch.repository;

import com.cs3ip.whattoresearch.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}

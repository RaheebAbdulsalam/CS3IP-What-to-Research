package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.exception.ResourceNotFoundException;
import com.cs3ip.whattoresearch.model.Contact;
import com.cs3ip.whattoresearch.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllMessages() {
        return contactRepository.findAll();
    }

    public Contact getMessageById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + id));
    }

    public void deleteMessage(Long id) {
        contactRepository.deleteById(id);
    }
}
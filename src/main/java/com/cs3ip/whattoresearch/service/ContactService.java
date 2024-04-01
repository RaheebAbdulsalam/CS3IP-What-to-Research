package com.cs3ip.whattoresearch.service;

import com.cs3ip.whattoresearch.exception.ResourceNotFoundException;
import com.cs3ip.whattoresearch.model.Contact;
import com.cs3ip.whattoresearch.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing messages.
 */
@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    /**
     * Retrieves a list of all messages.
     * @return List of Contact objects representing all messages.
     */
    public List<Contact> getAllMessages() {
        return contactRepository.findAll();
    }

    /**
     * Retrieves a contact message by its ID.
     * @param id The ID of the contact message to retrieve.
     * @return The Contact object representing the message with the specified ID.
     * @throws ResourceNotFoundException If the contact message with the given ID is not found.
     */
    public Contact getMessageById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + id));
    }

    /**
     * Deletes a message by its ID.
     * @param id The ID of the message to delete.
     */
    public void deleteMessage(Long id) {
        contactRepository.deleteById(id);
    }
}
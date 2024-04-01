package com.cs3ip.whattoresearch.controller.admin;


import com.cs3ip.whattoresearch.model.Contact;
import com.cs3ip.whattoresearch.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * RESTful Controller for controlling contact messages.
 */
@RestController
@RequestMapping("/admin/messages")
public class AdminContactController {

    @Autowired
    private ContactService contactService;

    /**
     * Retrieves the admin messages page displaying a list of messages.
     *
     * @return A ModelAndView object for the admin messages page view.
     */
    @GetMapping
    public ModelAndView getAdminMessagesPage() {
        ModelAndView mav = new ModelAndView("admin/messages");
        mav.addObject("messages", contactService.getAllMessages());
        return mav;
    }

    /**
     * Retrieves the page displaying details of a specific message.
     *
     * @param id The ID of the message.
     * @return A ModelAndView object for the message details page view.
     */
    @GetMapping("/show/{id}")
    public ModelAndView showMessage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/messageDetails");
        Contact contact = contactService.getMessageById(id);
        mav.addObject("messages", contact);
        return mav;
    }

    /**
     * Delete a message.
     *
     * @param id The ID of the message to be deleted.
     * @return A RedirectView object redirecting to the admin messages page.
     */
    @PostMapping("/{id}")
    public RedirectView deleteMessages(@PathVariable("id") Long id) {
        contactService.deleteMessage(id);
        return new RedirectView("/admin/messages");
    }


}


package com.cs3ip.whattoresearch.controller.admin;


import com.cs3ip.whattoresearch.model.Contact;
import com.cs3ip.whattoresearch.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@RestController
@RequestMapping("/admin/messages")
public class AdminContactController {

    @Autowired
    private ContactService contactService;

    // Returns admin messages page and messages list
    @GetMapping
    public ModelAndView getAdminMessagesPage() {
        ModelAndView mav = new ModelAndView("admin/messages");
        mav.addObject("messages", contactService.getAllMessages());
        return mav;
    }

    // method for displaying a message by id
    @GetMapping("/show/{id}")
    public ModelAndView showMessage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/messageDetails");
        Contact contact = contactService.getMessageById(id);
        mav.addObject("messages", contact);
        return mav;
    }

    // method for deleting messages, and reloading page
    @PostMapping("/{id}")
    public RedirectView deleteMessages(@PathVariable("id") Long id) {
        contactService.deleteMessage(id);
        return new RedirectView("/admin/messages");
    }


}


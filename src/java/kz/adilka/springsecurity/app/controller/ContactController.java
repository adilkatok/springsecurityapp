package kz.adilka.springsecurity.app.controller;

import kz.adilka.springsecurity.app.model.Contact;
import kz.adilka.springsecurity.app.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for {@link kz.adilka.springsecurity.app.model.Contact}'s pages
 *
 * @author Adilka Tokushev
 * @version 1.0
 */

@Controller
public class ContactController {

    private ContactService contactService;

    @Autowired(required = true)
    @Qualifier(value = "contactService")
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String listContactsForAdmin(Model model) {
        model.addAttribute("contact", new Contact());
        model.addAttribute("listContacts", this.contactService.listContacts());
        return "admin";
    }
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String listContactsForUser(Model model) {
        model.addAttribute("contact", new Contact());
        model.addAttribute("listContacts", this.contactService.listContacts());
        return "welcome";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contact") Contact contact) {
        if (contact.getId() == 0) {
            this.contactService.addContact(contact);
        } else {
            this.contactService.updateContact(contact);
        }
        return "redirect:/admin";
    }

    @RequestMapping("/remove/{id}")
    public String removeContact(@PathVariable("id") int id) {
        this.contactService.removeContact(id);

        return "redirect:/admin";
    }

    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("contact", this.contactService.getContactById(id));
        model.addAttribute("listContacts", this.contactService.listContacts());

        return "admin";
    }

    @RequestMapping("contactData/{id}")
    public String contactData(@PathVariable("id") int id, Model model) {
        model.addAttribute("contact", this.contactService.getContactById(id));

        return "contactData";
    }

}

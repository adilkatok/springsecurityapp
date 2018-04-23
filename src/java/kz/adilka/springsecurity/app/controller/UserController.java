package kz.adilka.springsecurity.app.controller;

import kz.adilka.springsecurity.app.model.Contact;
import kz.adilka.springsecurity.app.model.User;
import kz.adilka.springsecurity.app.service.ContactService;
import kz.adilka.springsecurity.app.service.SecurityService;
import kz.adilka.springsecurity.app.service.UserService;
import kz.adilka.springsecurity.app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for {@link kz.adilka.springsecurity.app.model.User}'s pages.
 *
 * @author Adilka Tokushev
 * @version 1.0
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ContactService contactService;

    @Autowired(required = true)
    @Qualifier(value = "contactService")
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm")
                                           User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if(error!=null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout!=null) {
            model.addAttribute("message", "logged out successfully");
        }

        return "login";
    }

    @RequestMapping(value = "welcome", method = RequestMethod.GET)
    public  String listContacts(Model model) {
        model.addAttribute("contact", new Contact());
        model.addAttribute("listContacts", this.contactService.listContacts());
        return "welcome";
    }

//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public String admin(Model model) {
//        model.addAttribute("contact", new Contact());
//        model.addAttribute("listContacts", this.contactService.listContacts());
//        return "admin";
//    }

    @RequestMapping(value = "/welcome/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contact") Contact contact) {
        if (contact.getId() == 0) {
            this.contactService.addContact(contact);
        } else {
            this.contactService.updateContact(contact);
        }
        return "redirect:/welcome";
    }

    @RequestMapping("/remove/{id}")
    public String removeContact(@PathVariable("id") int id) {
        this.contactService.removeContact(id);

        return "redirect:/welcome";
    }

    @RequestMapping("edit/{id}")
    public String editContact(@PathVariable("id") int id, Model model) {
        model.addAttribute("contact", this.contactService.getContactById(id));
        model.addAttribute("listContacts", this.contactService.listContacts());
        return "welcome";
    }

    @RequestMapping("contactData/{id}")
    public String contactData(@PathVariable("id") int id, Model model) {
        model.addAttribute("contact", this.contactService.getContactById(id));
        return "contactData";
    }

    /**
     * Handle request for downloading Excel document
     */
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView downloadExcel() {
        return new ModelAndView("excelView", "listContacts", contactService.listContacts());
    }

    @RequestMapping(value = "/downloadDoc", method = RequestMethod.GET)
    public ModelAndView downloadDoc() {
        return new ModelAndView("docView", "listContacts", contactService.listContacts());
    }

    /**
     * handle request for searching from {@link Contact}
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchForCity(@PathVariable() String contactCity, Model model) {
        return "welcome";
    }
}

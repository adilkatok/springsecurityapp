package kz.adilka.springsecurity.app.service;

import kz.adilka.springsecurity.app.model.Contact;

import java.util.List;

/**
 * Service class for {@link Contact}
 *
 * @author Adilka Tokushev
 * @version 1.0
 */
public interface ContactService {
    void addContact(Contact contact);
    void updateContact(Contact contact);
    void removeContact(int id);
    Contact getContactById(int id);
    List<Contact> listContacts();
}

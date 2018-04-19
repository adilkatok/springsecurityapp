package kz.adilka.springsecurity.app.dao;


import kz.adilka.springsecurity.app.model.Contact;

import java.util.List;

public interface ContactDao {
    void addContact(Contact contact);

    void updateContact(Contact contact);

    void removeContact(int id);

    Contact getContactById(int id);

    List<Contact> listContacts();
}

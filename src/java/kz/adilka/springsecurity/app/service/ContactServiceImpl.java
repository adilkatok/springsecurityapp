package kz.adilka.springsecurity.app.service;

import kz.adilka.springsecurity.app.dao.ContactDao;
import kz.adilka.springsecurity.app.model.Contact;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of {@link ContactService} interface
 *
 * @author Adilka Tokushev
 * @version 1.0
 */

@Service
public class ContactServiceImpl implements ContactService {

    private ContactDao contactDao;

    public void setContactDao(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    @Transactional
    public void addContact(Contact contact) {
        this.contactDao.addContact(contact);
    }

    @Override
    @Transactional
    public void updateContact(Contact contact) {
        this.contactDao.updateContact(contact);
    }

    @Override
    @Transactional
    public void removeContact(int id) {
        this.contactDao.removeContact(id);
    }

    @Override
    @Transactional
    public Contact getContactById(int id) {
        return this.contactDao.getContactById(id);
    }

    @Override
    @Transactional
    public List<Contact> listContacts() {
        return this.contactDao.listContacts();
    }
}

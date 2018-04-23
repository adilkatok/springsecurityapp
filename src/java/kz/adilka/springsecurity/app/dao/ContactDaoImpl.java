package kz.adilka.springsecurity.app.dao;

import kz.adilka.springsecurity.app.model.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.List;

public class ContactDaoImpl implements ContactDao {
    //private static final Logger logger = LoggerFactory.getLogger(ContactDaoImpl.class);

    private SessionFactory sessionFactory;
    private String contactDao;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setContactDao(String contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public void addContact(Contact contact) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(contact);
       // logger.info("Contact successfully added. Contact details: " + contact);
    }

    @Override
    public void updateContact(Contact contact) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(contact);
        //logger.info("Contact successfully updated. Contact details: " + contact);
    }

    @Override
    public void removeContact(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Contact contact = (Contact) session.load(Contact.class, new Integer(id));

        if (contact!=null) {
            session.delete(contact);
        }
        //logger.info("Contact successfully deleted. Contact details: " + contact);
    }

    @Override
    public Contact getContactById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Contact contact = (Contact) session.load(Contact.class, new Integer(id));
        //logger.info("Contact successfully loaded. Contact details: " + contact);

        return contact;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Contact> listContacts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Contact> contactList = session.createQuery("FROM Contact").list();

//        for (Contact contact: contactList) {
//            logger.info("Contact List" + contact);
//        }
        return contactList;
    }
}

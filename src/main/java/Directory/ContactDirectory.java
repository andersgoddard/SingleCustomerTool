package Directory;

import java.util.List;
import java.util.ArrayList;

import Associaters.Associatable;
import Contact.Contact;
import ContactInfo.Info;
import ContactInfo.ContactInfoItem;

/* A singleton class representing all of the Contacts */
public class ContactDirectory implements Directory, ContactRetriever {
    private final List<Associatable> contacts;
    private static ContactDirectory list = null;

    ContactDirectory(){
        this.contacts = new ArrayList<>();
    }

    public static ContactDirectory getInstance() {
        if (list == null) {
            list = new ContactDirectory();
        }

        return list;
    }

    @Override
    public void clear() {
        list = null;
        contacts.clear();
    }

    @Override
    public int size() {
        return contacts.size();
    }

    @Override
    public void add(Associatable contact) {
        contacts.add(contact);
    }

    @Override
    public List<Associatable> get() {
        return contacts;
    }

    @Override
    public boolean doesNotContain(ContactInfoItem item) {
        return true;
    }

    @Override
    public Contact contains(String name, Info info) {
        Directory companies = CompanyDirectory.getInstance();
        for (Associatable associatable : contacts){
            Contact contact = (Contact)associatable;
            for (ContactInfoItem item : info.getItems()){
                if ((contact.hasContactInfoItem(item))
                        && (companies.doesNotContain(item) || contact.getName().equals(name)))
                    return contact;
            }
        }
        return null;
    }

    @Override
    public List<Contact> getContactsWith(String emailDomain) {
        List<Contact> associatedContacts = new ArrayList<>();

        for (Associatable associatable : contacts){
            Contact contact = (Contact)associatable;
            if (contact.hasEmailDomain(emailDomain))
                associatedContacts.add(contact);
        }

        return associatedContacts;
    }

    @Override
    public List<Contact> getContactsWith(Info sharedContactInfo) {
        List<Contact> associatedContacts = new ArrayList<>();
        for (Associatable associatable : contacts){
            Contact contact = (Contact)associatable;
            if (contact.hasContactInfoItemIn(sharedContactInfo))
                associatedContacts.add(contact);
        }
        return associatedContacts;
    }
}

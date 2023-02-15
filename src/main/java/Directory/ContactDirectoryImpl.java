package Directory;

import java.util.List;
import java.util.ArrayList;

import Contact.Contact;
import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;

/* A singleton class representing all of the Contacts */
public class ContactDirectoryImpl implements ContactDirectory {
    private final List<Contact> contacts;
    private static ContactDirectoryImpl list = null;

    ContactDirectoryImpl(){
        this.contacts = new ArrayList<>();
    }

    public static ContactDirectoryImpl getInstance() {
        if (list == null) {
            list = new ContactDirectoryImpl();
        }

        return list;
    }

    @Override
    public void clear() {
        list = null;
        contacts.clear();
    }

    @Override
    public void add(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public int size() {
        return contacts.size();
    }

    @Override
    public List<Contact> get() {
        return contacts;
    }

    @Override
    public boolean doesNotContain(ContactInfoItem item) {
        return true; // This needs implementing if ever used
    }

    @Override
    public Contact contains(String name, ContactInfo info) {
        Directory companies = CompanyDirectoryImpl.getInstance();
        for (Contact contact : contacts){
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

        for (Contact contact : contacts){
            if (contact.hasEmailDomain(emailDomain))
                associatedContacts.add(contact);
        }

        return associatedContacts;
    }

    @Override
    public List<Contact> getContactsWith(ContactInfo sharedContactInfo) {
        List<Contact> associatedContacts = new ArrayList<>();
        for (Contact contact : contacts){
            if (contact.hasContactInfoItemIn(sharedContactInfo))
                associatedContacts.add(contact);
        }
        return associatedContacts;
    }
}

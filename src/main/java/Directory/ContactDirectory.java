package Directory;

import java.util.List;
import java.util.ArrayList;

import Associaters.Associatable;
import Contact.Contact;
import Contact.ContactSplitter;
import ContactInfo.Info;
import ContactInfo.ContactInfoItem;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/* A singleton class representing all of the Contacts */
public class ContactDirectory implements  Directory {
    private final List<Associatable> contacts;
    private static ContactDirectory list = null;

    ContactDirectory(){
        this.contacts = new ArrayList<>();
    }

    @Provides
    @Singleton
    public static ContactDirectory getInstance() {
        if (list == null) {
            list = new ContactDirectory();
        }

        return list;
    }

    public static void separateIncorrectlyMergedContacts() {
            ContactDirectory contacts = ContactDirectory.getInstance();
            ContactSplitter splitter = new ContactSplitter();
            for (Associatable associatable : contacts.get()) {
                Contact contact = (Contact)associatable;
                List<Contact> children = contact.getChildContacts();
                List<Contact> removedChildren = new ArrayList<>();
                if (children != null) {
                    for (Contact child : children) {
                        splitter.split(child);
                        removedChildren.add(child);
                    }
                    contact.removeFromChildContacts(removedChildren);
                }
            }
        }

    /*    Clears the global list of Contacts. Necessary for the unit tests. */
    public void clear() {
        list = null;
        contacts.clear();
    }

    public int size() {
        return contacts.size();
    }

    @Override
    public void add(Associatable contact) {
        contacts.add(contact);
    }

/*  Loops through all Contacts in the ContactList and checks whether there is any crossover in the contact information.
*   Returns the Contact containing a ContactInfoItem in the info parameter, otherwise null
*/
    public Contact contains(String name, Info info) {
        CompanyDirectory companies = CompanyDirectory.getInstance();
        for (Associatable associatable : contacts){
            Contact contact = (Contact)associatable;
            for (ContactInfoItem item : info.getItems()){
                if ((contact.hasContactInfoItem(item)) && (companies.doesNotContain(item) || contact.getName().equals(name)))
                    return contact;
            }
        }
        return null;
    }

    public List<Contact> getContactsWith(String emailDomain) {
        List<Contact> associatedContacts = new ArrayList<>();

        for (Associatable associatable : contacts){
            Contact contact = (Contact)associatable;
            if (contact.hasEmailDomain(emailDomain))
                associatedContacts.add(contact);
        }

        return associatedContacts;
    }

    public List<Contact> getContactsWith(Info sharedContactInfo) {
        List<Contact> associatedContacts = new ArrayList<>();
        for (Associatable associatable : contacts){
            Contact contact = (Contact)associatable;
            if (contact.hasContactInfoItemIn(sharedContactInfo))
                associatedContacts.add(contact);
        }
        return associatedContacts;
    }

    public List<Associatable> get() {
        return contacts;
    }
}

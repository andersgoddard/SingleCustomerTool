package Contact;

import java.util.List;
import java.util.ArrayList;

import ContactInfo.Info;
import ContactInfo.ContactInfoItem;
import Group.CompanyList;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/* A singleton class representing all of the Contacts */
public class ContactList implements IContactList {
    private static ArrayList<Contact> contacts;
    private static ContactList list = null;

    @Provides
    @Singleton
    public static ContactList getInstance() {
        if (list == null) {
            list = new ContactList();
            contacts = new ArrayList<>();
        }

        return list;
    }

    public static void separateIncorrectlyMergedContacts() {
            ContactList contacts = ContactList.getInstance();
            for (Contact contact : contacts.get()) {
                List<Contact> children = contact.getChildContacts();
                List<Contact> removedChildren = new ArrayList<>();
                if (children != null) {
                    for (Contact child : children) {
                        ContactSplitter.split(child);
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

    public void add(Contact contact) {
        contacts.add(contact);
    }


/*  Loops through all Contacts in the ContactList and checks whether there is any crossover in the contact information.
*   Returns the Contact containing a ContactInfoItem in the info parameter, otherwise null
*/
    public Contact contains(String name, Info info) {
        CompanyList companies = CompanyList.getInstance();
        for (Contact contact : contacts){
            for (ContactInfoItem item : info.getItems()){
                if ((contact.hasContactInfoItem(item)) && (companies.doesNotContain(item) || contact.getName().equals(name)))
                    return contact;
            }
        }
        return null;
    }

    public List<Contact> getContactsWith(String emailDomain) {
        List<Contact> associatedContacts = new ArrayList<>();

        for (Contact contact : contacts){
            if (contact.hasEmailDomain(emailDomain))
                associatedContacts.add(contact);
        }

        return associatedContacts;
    }

    public List<Contact> getContactsWith(Info sharedContactInfo) {
        List<Contact> associatedContacts = new ArrayList<>();
        for (Contact contact : contacts){
            if (contact.hasContactInfoItemIn(sharedContactInfo))
                associatedContacts.add(contact);
        }
        return associatedContacts;
    }

    public List<Contact> get() {
        return contacts;
    }
}

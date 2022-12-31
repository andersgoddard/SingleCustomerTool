package Contact;

import java.util.ArrayList;

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;
import Group.CompanyList;

/* A singleton class representing all of the Contacts */
public class ContactList {
    private final ArrayList<Contact> contacts;
    private static ContactList list = null;

    private ContactList() {
        contacts = new ArrayList<>();
    }

    public static ContactList getInstance() {
        if (list == null)
            list = new ContactList();

        return list;
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
    public Contact contains(ContactInfo info) {
        CompanyList companies = CompanyList.getInstance();
        for (Contact contact : contacts){
            for (ContactInfoItem item : info.getItems()){
                if (contact.hasContactInfoItem(item) && companies.doesNotContain(item))
                    return contact;
            }
        }
        return null;
    }

    public ArrayList<Contact> getContactsWith(String emailDomain) {
        ArrayList<Contact> associatedContacts = new ArrayList<>();

        for (Contact contact : contacts){
            if (contact.hasEmailDomain(emailDomain))
                associatedContacts.add(contact);
        }

        return associatedContacts;
    }

    public ArrayList<Contact> getContactsWith(ContactInfo sharedContactInfo) {
        ArrayList<Contact> associatedContacts = new ArrayList<>();
        for (Contact contact : contacts){
            if (contact.hasContactInfoItemIn(sharedContactInfo))
                associatedContacts.add(contact);
        }
        return associatedContacts;
    }

    public ArrayList<Contact> get() {
        return contacts;
    }
}

package Group;

import Contact.Contact;
import Contact.ContactList;

import java.util.ArrayList;

public class ContactAssociater implements Associater {

    public static void associate(Company company) {
        ArrayList<Contact> associatedContacts = getAssociatedContacts(company);
        for (Contact contact : associatedContacts) {
            contact.setCompanyId(company.getUniqueIdentifier());
        }
    }

    private static ArrayList<Contact> getAssociatedContacts(Company company){
        ContactList list = ContactList.getInstance();
        ArrayList<Contact> associatedContacts = new ArrayList<>();
        associatedContacts.addAll(list.getContactsWith(company.getEmailDomain()));
        associatedContacts.addAll(list.getContactsWith(company.getSharedContactInfo()));
        return associatedContacts;
    }
}

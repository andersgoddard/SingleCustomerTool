package Associaters;

import Contact.Contact;
import Directory.ContactDirectory;
import Group.Company;

import java.util.ArrayList;

public class ContactAssociater implements Associater {

    private ContactAssociater(){}
    public static ContactAssociater create(){
        return new ContactAssociater();
    }

    public void associate(Associatable associatable) {
        Company company = (Company)associatable;
        ArrayList<Contact> associatedContacts = getAssociatedContacts(company);
        for (Contact contact : associatedContacts) {
            contact.setCompanyId(company.getUniqueIdentifier());
        }
    }

    private static ArrayList<Contact> getAssociatedContacts(Company company){
        ContactDirectory list = ContactDirectory.getInstance();
        ArrayList<Contact> associatedContacts = new ArrayList<>();
        associatedContacts.addAll(list.getContactsWith(company.getEmailDomain()));
        associatedContacts.addAll(list.getContactsWith(company.getSharedContactInfo()));
        return associatedContacts;
    }
}

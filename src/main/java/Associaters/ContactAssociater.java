package Associaters;

import Contact.Contact;
import Directory.ContactDirectory;
import Directory.ContactInfoIdentifier;
import Group.Company;

import java.util.List;
import java.util.ArrayList;

public class ContactAssociater implements Associater {

    private ContactAssociater(){}
    public static ContactAssociater create(){
        return new ContactAssociater();
    }

    public void associate(Associatable associatable) {
        Company company = (Company)associatable;
        List<Contact> associatedContacts = getAssociatedContacts(company);
        for (Contact contact : associatedContacts) {
            contact.setCompanyId(company.getUniqueIdentifier());
        }
    }

    private static List<Contact> getAssociatedContacts(Company company){
        ContactInfoIdentifier directory = ContactDirectory.getInstance();
        List<Contact> associatedContacts = new ArrayList<>();
        associatedContacts.addAll(directory.getContactsWith(company.getEmailDomain()));
        associatedContacts.addAll(directory.getContactsWith(company.getSharedContactInfo()));
        return associatedContacts;
    }
}

package Associaters;

import Contact.Contact;
import Directory.ContactDirectory;
import Directory.ContactDirectoryImpl;
import Group.Company;

import java.util.List;
import java.util.ArrayList;

public class ContactAssociaterImpl implements ContactAssociater {

    private ContactAssociaterImpl(){}
    public static ContactAssociaterImpl create(){
        return new ContactAssociaterImpl();
    }

    public void associate(Company company) {
        List<Contact> associatedContacts = getAssociatedContacts(company);
        for (Contact contact : associatedContacts) {
            contact.setCompanyId(company.getUniqueIdentifier());
        }
    }

    private List<Contact> getAssociatedContacts(Company company){
        ContactDirectory directory = ContactDirectoryImpl.getInstance();
        List<Contact> associatedContacts = new ArrayList<>();
        associatedContacts.addAll(directory.getContactsWith(company.getEmailDomain()));
        associatedContacts.addAll(directory.getContactsWith(company.getSharedContactInfo()));
        return associatedContacts;
    }
}

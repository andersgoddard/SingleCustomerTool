package Associaters;

import Contact.Contact;
import Directory.ContactDirectory;
import Directory.ContactDirectoryImpl;
import Group.CompanyImpl;

import java.util.List;
import java.util.ArrayList;

public class ContactAssociaterImpl implements Associater<CompanyImpl> {

    private ContactAssociaterImpl(){}
    public static ContactAssociaterImpl create(){
        return new ContactAssociaterImpl();
    }

    public void associate(CompanyImpl company) {
        List<Contact> associatedContacts = getAssociatedContacts(company);
        for (Contact contact : associatedContacts) {
            contact.setCompanyId(company.getUniqueIdentifier());
        }
    }

    private List<Contact> getAssociatedContacts(CompanyImpl company){
        ContactDirectory directory = ContactDirectoryImpl.getInstance();
        List<Contact> associatedContacts = new ArrayList<>();
        associatedContacts.addAll(directory.getContactsWith(company.getEmailDomain()));
        associatedContacts.addAll(directory.getContactsWith(company.getSharedContactInfo()));
        return associatedContacts;
    }
}

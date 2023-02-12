package Associaters;

import Contact.ContactImpl;
import Directory.ContactDirectory;
import Directory.ContactRetriever;
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
        List<ContactImpl> associatedContacts = getAssociatedContacts(company);
        for (ContactImpl contact : associatedContacts) {
            contact.setCompanyId(company.getUniqueIdentifier());
        }
    }

    private static List<ContactImpl> getAssociatedContacts(Company company){
        ContactRetriever directory = ContactDirectory.getInstance();
        List<ContactImpl> associatedContacts = new ArrayList<>();
        associatedContacts.addAll(directory.getContactsWith(company.getEmailDomain()));
        associatedContacts.addAll(directory.getContactsWith(company.getSharedContactInfo()));
        return associatedContacts;
    }
}

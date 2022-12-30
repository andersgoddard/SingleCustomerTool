package Group;

import Contact.ContactList;
import Contact.Contact;

import java.util.ArrayList;
import java.util.UUID;

public class Company implements Group {
    String name;
    String companyId;
    String emailDomain;

    private Company(String name) {
        this.name = name;
        this.companyId = UUID.randomUUID().toString();
        CompanyList.getInstance().add(this);
    }

    private void associateContacts() {
        ContactList list = ContactList.getInstance();
        ArrayList<Contact> associatedContacts = list.getContactsWith(emailDomain);
        for (Contact contact : associatedContacts) {
            contact.setCompanyId(companyId);
        }
    }

    public static Company create(String name) {
        return new Company(name);
    }

    public static Company create(String name, String emailDomain){
        Company company = new Company(name);
        company.setEmailDomain(emailDomain);
        return company;
    }

    public String get() {
        return name;
    }

    public String getUniqueIdentifier() {
        return companyId;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
        associateContacts();
    }

    public String getEmailDomain() {
        return emailDomain;
    }
}
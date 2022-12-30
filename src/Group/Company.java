package Group;

import Contact.ContactList;
import Contact.Contact;
import ContactInfo.PhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Company implements Group {
    String name;
    String companyId;
    String emailDomain;
    List<PhoneNumber> sharedPhoneNumbers;

    private Company(String name) {
        this.name = name;
        this.companyId = UUID.randomUUID().toString();
        this.sharedPhoneNumbers = new ArrayList<>();
        CompanyList.getInstance().add(this);
    }

    private void associateContacts() {
        ContactList list = ContactList.getInstance();
        ArrayList<Contact> associatedContacts = new ArrayList<>();
        associatedContacts.addAll(list.getContactsWith(emailDomain));
        associatedContacts.addAll(list.getContactsWith(sharedPhoneNumbers));
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

    @Override
    public void setSharedPhoneNumbers(String number) {
        sharedPhoneNumbers.add(PhoneNumber.create(number));
        associateContacts();
    }

    @Override
    public void setSharedPhoneNumbers(List<PhoneNumber> numbers) {
        sharedPhoneNumbers.addAll(numbers);
        associateContacts();
    }

    @Override
    public List<PhoneNumber> getSharedPhoneNumbers() {
        return sharedPhoneNumbers;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
        associateContacts();
    }

    public String getEmailDomain() {
        return emailDomain;
    }
}
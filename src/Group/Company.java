package Group;

import Contact.ContactList;
import Contact.Contact;
import ContactInfo.ContactInfoItem;
import ContactInfo.ContactInfoParser;
import ContactInfo.ContactInfo;
import ContactInfo.PhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Company implements Group {
    String name;
    String companyId;
    String emailDomain;
    List<PhoneNumber> sharedPhoneNumbers;
    ContactInfo sharedContactInfo;

    private Company(String name) {
        this.name = name;
        this.companyId = UUID.randomUUID().toString();
        this.sharedPhoneNumbers = new ArrayList<>();
        this.sharedContactInfo = new ContactInfo();
        CompanyList.getInstance().add(this);
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

    private void associateContacts() {
        ArrayList<Contact> associatedContacts = getAssociatedContacts();
        for (Contact contact : associatedContacts) {
            contact.setCompanyId(companyId);
        }
    }

    private ArrayList<Contact> getAssociatedContacts(){
        ContactList list = ContactList.getInstance();
        ArrayList<Contact> associatedContacts = new ArrayList<>();
        associatedContacts.addAll(list.getContactsWith(emailDomain));
        associatedContacts.addAll(list.getContactsWith(sharedContactInfo));
        return associatedContacts;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
        associateContacts();
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    public boolean hasSharedContactInfoItem(ContactInfoItem item) {
        for (ContactInfoItem existingItem : sharedContactInfo.getItems()){
            if (existingItem.get().equals(item.get()))
                return true;
        }
        return false;
    }

    @Override
    public void setSharedContactInfo(String item) {
        this.sharedContactInfo.add(ContactInfoParser.parse(item).get(0));
        associateContacts();
    }

    @Override
    public void setSharedContactInfo(ContactInfo info) {
        this.sharedContactInfo.addAll(info);
        associateContacts();
    }

    @Override
    public ContactInfo getSharedContactInfo() {
        return sharedContactInfo;
    }
}
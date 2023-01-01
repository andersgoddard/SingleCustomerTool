package Group;

import Associaters.Associatable;
import Associaters.ContactAssociater;
import Contact.ContactList;
import ContactInfo.ContactInfoItem;
import ContactInfo.ContactInfoParser;
import ContactInfo.ContactInfo;

import java.util.UUID;

public class Company implements Group, Associatable {
    String name;
    String companyId;
    String emailDomain;
    ContactInfo sharedContactInfo;

    private Company(String name) {
        this.name = name;
        this.companyId = UUID.randomUUID().toString();
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

    // Getter Methods
    public String getName() {
        return name;
    }

    public String getUniqueIdentifier() {
        return companyId;
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    @Override
    public ContactInfo getSharedContactInfo() {
        return sharedContactInfo;
    }

    public boolean hasSharedContactInfoItem(ContactInfoItem item) {
        for (ContactInfoItem existingItem : sharedContactInfo.getItems()){
            if (existingItem.get().equals(item.get()))
                return true;
        }
        return false;
    }

    // Setter Methods
    @Override
    public void setSharedContactInfo(String item) {
        ContactInfo info = new ContactInfo();
        info.add(ContactInfoParser.parse(item).get(0));
        setSharedContactInfo(info);
    }

    @Override
    public void setSharedContactInfo(ContactInfo info) {
        this.sharedContactInfo.addAll(info);
        associateContacts();
        ContactList.separateIncorrectlyMergedContacts();
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
        associateContacts();
    }


    // Helper Methods
    private void associateContacts() {
        ContactAssociater.create().associate(this);
    }
}
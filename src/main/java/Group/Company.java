package Group;

import Associaters.ContactAssociaterImpl;
import Contact.ContactSplitter;
import ContactInfo.ContactInfoParser;
import Directory.CompanyDirectoryImpl;
import Directory.ContactDirectoryImpl;
import ContactInfo.ContactInfoItem;
import ContactInfo.ContactInfoParserImpl;
import ContactInfo.ContactInfoImpl;

import java.util.UUID;

public class Company implements Group {
    String name;
    String companyId;
    String emailDomain;
    ContactInfoImpl sharedContactInfo;

    public static Company create(String name) {
        return new Company(name);
    }

    public static Company create(String name, String emailDomain){
        Company company = new Company(name);
        company.setEmailDomain(emailDomain);
        return company;
    }

    private Company(String name) {
        this.name = name;
        this.companyId = UUID.randomUUID().toString();
        this.sharedContactInfo = new ContactInfoImpl();
        CompanyDirectoryImpl.getInstance().add(this);
    }

    public void setId(String id){

    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
        associateContacts();
    }

    private void associateContacts() {
        ContactAssociaterImpl.create().associate(this);
    }

    public String getName() {
        return name;
    }

    public String getUniqueIdentifier() {
        return companyId;
    }

    @Override
    public ContactInfoImpl getSharedContactInfo() {
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
        ContactInfoImpl info = new ContactInfoImpl();
        ContactInfoParser parser = new ContactInfoParserImpl();
        info.add(parser.parse(item).get(0));
        setSharedContactInfo(info);
    }

    @Override
    public void setSharedContactInfo(ContactInfoImpl info) {
        this.sharedContactInfo.addAll(info);
        associateContacts();
        separateIncorrectlyMergedContacts();
    }

    private void separateIncorrectlyMergedContacts(){
        ContactSplitter splitter = new ContactSplitter();
        splitter.separateIncorrectlyMergedContacts(ContactDirectoryImpl.getInstance());
    }

    public String getEmailDomain() {
        return emailDomain;
    }
}
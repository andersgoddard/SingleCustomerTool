package Group;

import Associaters.ContactAssociaterImpl;
import Contact.ContactSplitter; // Concrete dependency
import Contact.Splitter;
import ContactInfo.ContactInfoParser;
import Directory.CompanyDirectoryImpl; // Concrete dependency
import Directory.ContactDirectoryImpl; // Concrete dependency
import ContactInfo.ContactInfoItem;
import ContactInfo.ContactInfoParserImpl;
import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoImpl; // Concrete dependency

import java.util.UUID;

public class CompanyImpl implements Company {
    String name;
    String companyId;
    String emailDomain;
    ContactInfo sharedContactInfo;

    public static CompanyImpl create(String name) {
        return new CompanyImpl(name);
    }

    public static CompanyImpl create(String name, String emailDomain){
        CompanyImpl company = new CompanyImpl(name);
        company.setEmailDomain(emailDomain);
        return company;
    }

    private CompanyImpl(String name) {
        this.name = name;
        this.companyId = UUID.randomUUID().toString();
        this.sharedContactInfo = new ContactInfoImpl();
        CompanyDirectoryImpl.getInstance().add(this);
    }

    @Override
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
        ContactInfo info = new ContactInfoImpl();
        ContactInfoParser parser = new ContactInfoParserImpl();
        info.add(parser.parse(item).get(0));
        setSharedContactInfo(info);
    }

    @Override
    public void setSharedContactInfo(ContactInfo info) {
        this.sharedContactInfo.addAll(info);
        associateContacts();
        separateIncorrectlyMergedContacts();
    }

    private void separateIncorrectlyMergedContacts(){ // Don't think this is being tested and may have side effects
        Splitter splitter = new ContactSplitter();
        splitter.separateIncorrectlyMergedContacts(ContactDirectoryImpl.getInstance());
    }

    @Override
    public String getEmailDomain() {
        return emailDomain;
    }
}
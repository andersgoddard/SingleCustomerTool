package Group;

import Contact.ContactList;
import Contact.Contact;
import Contact.ContactSplitter;
import ContactInfo.ContactInfoItem;
import ContactInfo.ContactInfoParser;
import ContactInfo.ContactInfo;

import java.util.ArrayList;
import java.util.UUID;

public class Company implements Group {
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

    public String getName() {
        return name;
    }

    public String getUniqueIdentifier() {
        return companyId;
    }

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
        separateIncorrectlyMergedContacts();
    }

    private void separateIncorrectlyMergedContacts() {
        ContactList contacts = ContactList.getInstance();
        for (Contact contact : contacts.get()){
            ArrayList<Contact> children = contact.getChildContacts();
            ArrayList<Contact> removedChildren = new ArrayList<>();
            if (children != null){
                for (Contact child : children){
                    ContactSplitter.split(child);
                    removedChildren.add(child);
                }
                contact.removeFromChildContacts(removedChildren);
            }
        }
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
        associateContacts();
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
}
package Stubs;

import Contact.Contact;
import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;
import Directory.CompanyDirectory;
import Directory.CompanyDirectoryImpl;
import Directory.ContactDirectory;

import java.util.ArrayList;
import java.util.List;

public class ContactDirectoryStub implements ContactDirectory {
    List<Contact> contacts;

    public ContactDirectoryStub(){
        this.contacts = new ArrayList<>();
    }

    @Override
    public Contact contains(String name, ContactInfo info) {
        CompanyDirectory companies = CompanyDirectoryImpl.getInstance();
        for (Contact contact : contacts){
            for (ContactInfoItem item : info.getItems()){
                // Condition 1 - the contacts have a shared piece of contact info
                if ((contact.hasContactInfoItem(item))
                        // Condition 2a - the contact info isn't shared within a company - i.e. info@ email or switchboard number
                        // Condition 2b - the contacts have the same name
                        && (companies.doesNotContain(item) || contact.getName().equals(name)))
                    return contact;
            }
        }
        return null;
    }

    @Override
    public List<Contact> getContactsWith(String emailDomain) {
        return null;
    }

    @Override
    public List<Contact> getContactsWith(ContactInfo sharedContactInfo) {
        return null;
    }

    @Override
    public void add(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public List<Contact> get() {
        return null;
    }

    @Override
    public boolean doesNotContain(ContactInfoItem item) {
        return false;
    }
}

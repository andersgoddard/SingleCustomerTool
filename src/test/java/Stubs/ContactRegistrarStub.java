package Stubs;

import Contact.Contact;
import Contact.ContactRegistrar;
import Directory.ContactDirectory;

public class ContactRegistrarStub implements ContactRegistrar {
    ContactDirectory contacts;
    public ContactRegistrarStub(ContactDirectory contacts){
        this.contacts = contacts;
    }

    @Override
    public void register(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public ContactDirectory getContactDirectory() {
        return contacts;
    }
}

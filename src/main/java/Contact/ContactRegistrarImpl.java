package Contact;

import Directory.ContactDirectory;

public class ContactRegistrarImpl implements ContactRegistrar {
    ContactDirectory contacts;

    public ContactRegistrarImpl(ContactDirectory contacts){
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

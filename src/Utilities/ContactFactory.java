package Utilities;

import Associaters.Associater;
import Contact.Contact;
import DatabaseFields.DatabaseFields;
import Contact.UniqueIdentifierGenerator;
import Contact.ContactList;

public class ContactFactory implements Factory {
    UniqueIdentifierGenerator generator;
    Associater associater;
    ContactList contacts;

    public Contact create(DatabaseFields fields,
                          UniqueIdentifierGenerator generator,
                          Associater associater,
                          ContactList contacts) {
        this.generator = generator;
        this.associater = associater;
        this.contacts = contacts;
        Contact contact = Contact.create(fields);
        String uniqueIdentifier = getUniqueIdentifierFor(contact);
        contact.setNewUniqueIdentifier(uniqueIdentifier);
        associater.associate(contact);
        contacts.add(contact);
        return contact;
    }

    private String getUniqueIdentifierFor(Contact contact){
        return generator.getUniqueIdentifierFor(contact);
    }
}

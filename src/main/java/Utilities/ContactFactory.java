package Utilities;

import Associaters.Associater;
import Contact.Contact;
import DatabaseFields.DatabaseFields;
import Contact.UniqueIdentifierGenerator;
import Contact.ContactList;
import com.google.inject.Inject;

public class ContactFactory implements Factory {
    UniqueIdentifierGenerator generator;
    Associater associater;
    ContactList contacts;

    @Inject
    public ContactFactory(UniqueIdentifierGenerator generator,
                          Associater associater){
        this.generator = generator;
        this.associater = associater;
    }

    public Contact create(DatabaseFields fields) {
        contacts = ContactList.getInstance();
        System.out.println(contacts.size());
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

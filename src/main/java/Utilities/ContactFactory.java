package Utilities;

import Associaters.Associater;
import Contact.Contact;
import DatabaseFields.DatabaseFields;
import Contact.UniqueIdentifierGenerator;
import Contact.IContactList;
import com.google.inject.Inject;

public class ContactFactory implements Factory {
    UniqueIdentifierGenerator generator;
    Associater associater;
    IContactList contacts;

    @Inject
    public ContactFactory(UniqueIdentifierGenerator generator,
                          Associater associater,
                          IContactList contacts){
        this.generator = generator;
        this.associater = associater;
        this.contacts = contacts;
    }

    public Contact create(DatabaseFields fields) {
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

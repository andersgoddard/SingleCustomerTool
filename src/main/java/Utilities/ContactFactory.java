package Utilities;

import Associaters.Associater;
import Contact.ContactImpl;
import DatabaseFields.DatabaseFields;
import Contact.UniqueIdentifierGenerator;
import Directory.Directory;
import Directory.ContactDirectory;
import com.google.inject.Inject;

public class ContactFactory implements Factory {
    UniqueIdentifierGenerator generator;
    Associater associater;
    Directory contacts;

    @Inject
    public ContactFactory(UniqueIdentifierGenerator generator,
                          Associater associater){
        this.generator = generator;
        this.associater = associater;
    }

    public ContactImpl create(DatabaseFields fields) {
        contacts = ContactDirectory.getInstance();
        ContactImpl contact = ContactImpl.create(fields);
        String uniqueIdentifier = getUniqueIdentifierFor(contact);
        contact.setUniqueIdentifier(uniqueIdentifier);
        associater.associate(contact);
        contacts.add(contact);
        return contact;
    }

    private String getUniqueIdentifierFor(ContactImpl contact){
        return generator.getUniqueIdentifierFor(contact);
    }
}

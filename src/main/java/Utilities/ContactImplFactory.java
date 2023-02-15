package Utilities;

import Associaters.Associater;
import Contact.Contact;
import Contact.ContactImpl;
import DatabaseFields.DatabaseFields;
import Contact.UniqueIdentifierGenerator;
import Directory.Directory;
import Directory.ContactDirectoryImpl;
import com.google.inject.Inject;

public class ContactImplFactory implements Factory {
    UniqueIdentifierGenerator generator;
    Associater associater;
    Directory contacts;

    @Inject
    public ContactImplFactory(UniqueIdentifierGenerator generator,
                              Associater associater){
        this.generator = generator;
        this.associater = associater;
    }

    public ContactImpl create(DatabaseFields fields) {
        contacts = ContactDirectoryImpl.getInstance();
        ContactImpl contact = ContactImpl.create(fields);
        String uniqueIdentifier = getUniqueIdentifierFor(contact);
        contact.setUniqueIdentifier(uniqueIdentifier);
        associater.associate(contact);
        contacts.add(contact);
        return contact;
    }

    private String getUniqueIdentifierFor(Contact contact){
        return generator.getUniqueIdentifierFor(contact);
    }
}

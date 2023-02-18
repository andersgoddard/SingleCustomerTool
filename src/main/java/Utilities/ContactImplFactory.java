package Utilities;

import Associaters.CompanyAssociater;
import Contact.Contact;
import Contact.ContactImpl;
import DatabaseFields.DatabaseFields;
import Contact.UniqueIdentifierGenerator;
import Directory.ContactDirectory;
import Directory.ContactDirectoryImpl;
import com.google.inject.Inject;

public class ContactImplFactory implements Factory {
    UniqueIdentifierGenerator generator;
    CompanyAssociater associater;
    ContactDirectory contacts;

    @Inject
    public ContactImplFactory(UniqueIdentifierGenerator generator,
                              CompanyAssociater associater){
        this.generator = generator;
        this.associater = associater;
        this.contacts = ContactDirectoryImpl.getInstance(); // Ideally want Guice to inject this
    }

    public ContactImpl create(DatabaseFields fields) {
        ContactImpl contact = ContactImpl.create(fields);
        String uniqueIdentifier = getUniqueIdentifierFor(contact);
        contact.setUniqueIdentifier(uniqueIdentifier);
        associater.associate(contact);
        contacts.add(contact);
        return contact;
    }

    private String getUniqueIdentifierFor(Contact contact){
        return generator.getUniqueIdentifierFor(contact, contacts);
    }
}

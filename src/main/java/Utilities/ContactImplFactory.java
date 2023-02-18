package Utilities;

import Associaters.CompanyAssociater;
import Contact.Contact;
import Contact.ContactImpl;
import Contact.ContactRegistrar;
import DatabaseFields.DatabaseFields;
import Contact.UniqueIdentifierGenerator;
import com.google.inject.Inject;

public class ContactImplFactory implements Factory {
    UniqueIdentifierGenerator generator;
    CompanyAssociater associater;
    ContactRegistrar registerer;

    @Inject
    public ContactImplFactory(UniqueIdentifierGenerator generator,
                              CompanyAssociater associater){
        this.generator = generator;
        this.associater = associater;
    }

    public ContactImpl create(DatabaseFields fields, ContactRegistrar registerer) {
        ContactImpl contact = ContactImpl.create(fields);
        this.registerer = registerer;
        String uniqueIdentifier = getUniqueIdentifierFor(contact);
        contact.setUniqueIdentifier(uniqueIdentifier);
        associater.associate(contact);
        registerer.register(contact);
        return contact;
    }

    private String getUniqueIdentifierFor(Contact contact){
        return generator.getUniqueIdentifierFor(contact, registerer.getContactDirectory());
    }
}

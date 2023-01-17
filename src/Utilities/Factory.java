package Utilities;

import Associaters.Associater;
import Contact.Contact;
import DatabaseFields.DatabaseFields;
import Contact.UniqueIdentifierGenerator;
import Contact.ContactList;

public interface Factory {
    Contact create(DatabaseFields fields,
                   UniqueIdentifierGenerator generator,
                   Associater associater,
                   ContactList contacts);
}

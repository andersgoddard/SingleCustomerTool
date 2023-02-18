package Utilities;

import Contact.Contact;
import Contact.ContactRegistrar;
import DatabaseFields.DatabaseFields;

public interface Factory {
    Contact create(DatabaseFields fields, ContactRegistrar registerer);
}

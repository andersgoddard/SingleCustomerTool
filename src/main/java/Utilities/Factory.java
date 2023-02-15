package Utilities;

import Contact.Contact;
import DatabaseFields.DatabaseFields;

public interface Factory {
    Contact create(DatabaseFields fields);
}

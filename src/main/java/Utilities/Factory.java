package Utilities;

import Contact.ContactImpl;
import DatabaseFields.DatabaseFields;

public interface Factory {
    ContactImpl create(DatabaseFields fields);
}

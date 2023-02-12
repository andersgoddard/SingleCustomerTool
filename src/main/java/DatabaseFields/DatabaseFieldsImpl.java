package DatabaseFields;

import ContactInfo.ContactInfo;

public class DatabaseFieldsImpl implements DatabaseFields {
    String name;
    ContactInfo info;
    String primaryKey;

    public DatabaseFieldsImpl(String name, ContactInfo info, String primaryKey) {
        this.name = name;
        this.info = info;
        this.primaryKey = primaryKey;
    }

    public DatabaseFieldsImpl(String salutation, String firstName, String lastName,
                              ContactInfo info,
                              String primaryKey) {
        this.name = salutation + " " + firstName + " " + lastName;
        this.info = info;
        this.primaryKey = primaryKey;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ContactInfo getContactInfo() {
        return info;
    }

    @Override
    public String getPrimaryKey() {
        return primaryKey;
    }
}

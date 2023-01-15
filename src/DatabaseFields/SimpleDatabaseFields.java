package DatabaseFields;

import ContactInfo.ContactInfo;

public class SimpleDatabaseFields implements DatabaseFields {
    String name;
    ContactInfo info;
    String primaryKey;

    public SimpleDatabaseFields(String name) {
        this.name = name;
    }

    public SimpleDatabaseFields(String salutation, String firstName, String lastName) {
        this.name = salutation + " " + firstName + " " + lastName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setContactInfo(ContactInfo info) {
        this.info = info;
    }

    @Override
    public ContactInfo getContactInfo() {
        return info;
    }

    @Override
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public String getPrimaryKey() {
        return primaryKey;
    }
}

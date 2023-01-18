package DatabaseFields;

import ContactInfo.Info;

public class SimpleDatabaseFields implements DatabaseFields {
    String name;
    Info info;
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
    public void setContactInfo(Info info) {
        this.info = info;
    }

    @Override
    public Info getContactInfo() {
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

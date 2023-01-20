package DatabaseFields;

import ContactInfo.Info;

public class SimpleDatabaseFields implements DatabaseFields {
    String name;
    Info info;
    String primaryKey;

    public SimpleDatabaseFields(String name, Info info, String primaryKey) {
        this.name = name;
        this.info = info;
        this.primaryKey = primaryKey;
    }

    public SimpleDatabaseFields(String salutation, String firstName, String lastName,
                                Info info,
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
    public Info getContactInfo() {
        return info;
    }

    @Override
    public String getPrimaryKey() {
        return primaryKey;
    }
}

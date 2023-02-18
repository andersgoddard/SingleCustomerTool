package Stubs;

import ContactInfo.ContactInfo;
import DatabaseFields.DatabaseFields;

public class DatabaseFieldsStub implements DatabaseFields {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public ContactInfo getContactInfo() {
        return new ContactInfoStub();
    }

    @Override
    public String getPrimaryKey() {
        return null;
    }
}

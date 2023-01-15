package DatabaseFields;

import ContactInfo.ContactInfo;

public interface DatabaseFields {
    String getName();

    void setContactInfo(ContactInfo info);

    ContactInfo getContactInfo();

    void setPrimaryKey(String primaryKey);

    String getPrimaryKey();
}

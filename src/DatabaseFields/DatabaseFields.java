package DatabaseFields;

import ContactInfo.Info;

public interface DatabaseFields {
    String getName();

    void setContactInfo(Info info);

    Info getContactInfo();

    void setPrimaryKey(String primaryKey);

    String getPrimaryKey();
}

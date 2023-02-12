package Group;

import ContactInfo.ContactInfoImpl;

public interface Group {
    String getUniqueIdentifier();

    void setSharedContactInfo(String info);

    void setSharedContactInfo(ContactInfoImpl info);

    ContactInfoImpl getSharedContactInfo();
}

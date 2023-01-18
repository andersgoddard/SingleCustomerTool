package Group;

import ContactInfo.ContactInfo;

public interface Group {
    String getUniqueIdentifier();

    void setSharedContactInfo(String info);

    void setSharedContactInfo(ContactInfo info);

    ContactInfo getSharedContactInfo();
}

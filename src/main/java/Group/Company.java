package Group;

import ContactInfo.ContactInfoItem;

public interface Company extends Group {
    void setEmailDomain(String emailDomain);
    String getEmailDomain();

    String getName();

    boolean hasSharedContactInfoItem(ContactInfoItem item);
}

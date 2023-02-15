package Group;

import ContactInfo.ContactInfoItem;

public interface Company extends Group {
    void setEmailDomain(String emailDomain);
    String getEmailDomain();

    boolean hasSharedContactInfoItem(ContactInfoItem item);
}

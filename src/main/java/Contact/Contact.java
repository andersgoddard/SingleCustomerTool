package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;
import DatabaseFields.DatabaseFields;

import java.util.List;

public interface Contact {
    void setUniqueIdentifier();

    void setUniqueIdentifier(String uniqueIdentifier);

    String getUniqueIdentifier();

    boolean hasContactInfoItemIn(ContactInfo sharedContactInfo);

    boolean hasContactInfoItem(ContactInfoItem item);

    boolean hasEmailDomain(String emailDomain);

    void addToChildContacts(Contact contact);

    void removeFromChildContacts(List<Contact> children);

    String getName();

    void setCompanyId(String companyId);

    String getCompanyId();

    List<Contact> getChildContacts();

    DatabaseFields getDatabaseFields();
}

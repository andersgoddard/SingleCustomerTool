package Directory;

import Contact.ContactImpl;
import ContactInfo.ContactInfo;

import java.util.List;

public interface ContactRetriever {
    ContactImpl contains(String name, ContactInfo info);
    List<ContactImpl> getContactsWith(String emailDomain);
    List<ContactImpl> getContactsWith(ContactInfo sharedContactInfo);
}

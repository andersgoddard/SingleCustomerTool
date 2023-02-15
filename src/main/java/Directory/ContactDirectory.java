package Directory;

import Contact.Contact;
import ContactInfo.ContactInfo;

import java.util.List;

public interface ContactDirectory extends Directory<Contact> {
    Contact contains(String name, ContactInfo info);
    List<Contact> getContactsWith(String emailDomain);
    List<Contact> getContactsWith(ContactInfo sharedContactInfo);
}

package Directory;

import Contact.Contact;
import ContactInfo.Info;

import java.util.List;

public interface ContactRetriever {
    Contact contains(String name, Info info);
    List<Contact> getContactsWith(String emailDomain);
    List<Contact> getContactsWith(Info sharedContactInfo);
}

package Directory;

import Contact.Contact;
import ContactInfo.ContactInfoItem;
import ContactInfo.Info;

import java.util.List;

public interface ContactInfoIdentifier {
    default Contact contains(String name, Info info){
        return null;
    }
    default List<Contact> getContactsWith(String emailDomain){
        return null;
    }
    default List<Contact> getContactsWith(Info sharedContactInfo){
        return null;
    }
   default boolean doesNotContain(ContactInfoItem item){
       return false;
   }
}

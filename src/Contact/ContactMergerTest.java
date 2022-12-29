package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.PhoneNumber;
import ContactInfo.EmailAddress;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ContactMergerTest {
    @Test
    public void testMergeContacts(){
        ContactList list = ContactList.getInstance();
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        Contact andrew1 = Contact.create("Mr Andrew Goddard", info, "2000000");

        ContactInfo info2 = new ContactInfo();
        info2.add(EmailAddress.create("andersgoddard@gmail.com"));
        Contact andrew2 = Contact.create("Mr A Goddard", info2, "2000001");

        assertNotEquals(andrew1.getUniqueIdentifier(), andrew2.getUniqueIdentifier());
        ContactMerger.merge(andrew1, andrew2);
        assertEquals(andrew1.getUniqueIdentifier(), andrew2.getUniqueIdentifier());

        list.clear();
    }
}

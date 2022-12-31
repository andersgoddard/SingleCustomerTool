package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.PhoneNumber;
import ContactInfo.EmailAddress;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ContactSplitterTest {
    @Test
    public void testBreakUpContact(){
        ContactList list = ContactList.getInstance();
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(PhoneNumber.create("07746142639"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        Contact andrew = Contact.create("Mr Andrew Goddard", info, "2000000");

        ContactInfo info2 = new ContactInfo();
        info2.add(EmailAddress.create("indiabettsgoddard@outlook.com"));
        info2.add(PhoneNumber.create("07746142639"));
        Contact india = Contact.create("Mrs India Goddard", info2, "2000001");

        assertEquals(andrew.getUniqueIdentifier(), india.getUniqueIdentifier());
        ContactSplitter.split(andrew);
        assertNotEquals(andrew.getUniqueIdentifier(), india.getUniqueIdentifier());

        list.clear();
    }
}

import ContactInfo.ContactInfo;
import ContactInfo.EmailAddress;
import ContactInfo.PhoneNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactTest {
    @Test
    public void testBasicContact(){
        Contact contact = new Contact();
        contact.setTitle("Mr");
        contact.setFirstName("Andrew");
        contact.setLastName("Goddard");
        assertEquals("Mr Andrew Goddard", contact.getName());
    }

    @Test
    public void testContactAndContactInfo(){
        Contact contact = new Contact();
        contact.setTitle("Mr");
        contact.setFirstName("Andrew");
        contact.setLastName("Goddard");
        ContactInfo info = new ContactInfo();
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        info.add(PhoneNumber.create("07881266969"));
        contact.setContactInfo(info);
        assertTrue(contact.hasContactInfo(EmailAddress.create("andersgoddard@gmail.com")));
    }

    @Test
    public void testContactFullName(){
        Contact contact = new Contact();
        contact.setName("Mrs India Goddard");
        assertEquals("Mrs India Goddard", contact.getName());
    }
}

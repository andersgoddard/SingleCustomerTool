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
        info.add(new EmailAddress("andersgoddard@gmail.com"));
        info.add(new PhoneNumber("07881266969"));
        contact.setContactInfo(info);
        assertTrue(contact.hasContactInfo(new EmailAddress("andersgoddard@gmail.com")));
    }
}

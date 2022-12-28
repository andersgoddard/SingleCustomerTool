package ContactInfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactInfoTest {
    @Test
    public void testContactInfo(){
        ContactInfo info = new ContactInfo();
        info.add(new PhoneNumber("07881266969"));
        info.add(new PhoneNumber("01472210284"));
        info.add(new EmailAddress("andersgoddard@gmail.com"));
        info.add(new EmailAddress("agodda02@mail.bbk.ac.uk"));
        assertTrue(info.contains(new EmailAddress("andersgoddard@gmail.com")));
        assertFalse(info.contains(new EmailAddress("agoddard@kfh.co.uk")));
    }
}

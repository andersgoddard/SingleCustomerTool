package ContactInfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactInfoImplTest {
    @Test
    public void testContactInfo(){
        ContactInfo info = new ContactInfoImpl();
        info.add(PhoneNumber.create("07881266969"));
        info.add(PhoneNumber.create("01472210284"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        info.add(EmailAddress.create("agodda02@mail.bbk.ac.uk"));

        assertTrue(info.contains(EmailAddress.create("andersgoddard@gmail.com")));
        assertFalse(info.contains(EmailAddress.create("agoddard@kfh.co.uk")));
    }
}

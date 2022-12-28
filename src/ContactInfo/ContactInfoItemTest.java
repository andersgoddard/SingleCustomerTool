package ContactInfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactInfoItemTest {
    @Test
    public void testPhoneNumber(){
        ContactInfoItem number = new PhoneNumber("07881266969");
        assertEquals("07881266969", number.get());
    }

    @Test
    public void testEmailAddress(){
        ContactInfoItem email = new EmailAddress("andersgoddard@gmail.com");
        assertEquals("andersgoddard@gmail.com", email.get());
    }
}

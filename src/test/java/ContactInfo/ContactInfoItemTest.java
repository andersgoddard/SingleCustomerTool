package ContactInfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactInfoItemTest {
    @Test
    public void testPhoneNumber(){
        ContactInfoItem number = PhoneNumber.create("07881266969");

        assertEquals("07881266969", number.get());
    }

    @Test
    public void testEmailAddress(){
        ContactInfoItem email = EmailAddress.create("andersgoddard@gmail.com");

        assertEquals("andersgoddard@gmail.com", email.get());
    }
}

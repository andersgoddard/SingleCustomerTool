package ContactInfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailAddressTest {
    @Test
    public void testGetEmailAddress(){
        EmailAddress email = EmailAddress.create("andersgoddard@gmail.com");
        assertEquals("andersgoddard@gmail.com", email.get());
        assertTrue(email.isValid());
    }

    @Test
    public void testPlaceholderEmailAddress1(){
        EmailAddress email = EmailAddress.create("test@example.com");
        assertFalse(email.isValid());
    }

    @Test
    public void testPlaceholderEmailAddress2(){
        EmailAddress email = EmailAddress.create("na@na.com");
        assertFalse(email.isValid());
    }

    @Test
    public void testParseNoisyEmail(){
        EmailAddress email = EmailAddress.create("annabel_tino@hotmail.com (Business)");
        assertEquals("annabel_tino@hotmail.com", email.get());
    }

    @Test
    public void testCapitalEmail(){
        EmailAddress email = EmailAddress.create("ANDERSGODDARD@GMAIL.COM");
        assertEquals("andersgoddard@gmail.com", email.get());
    }
}

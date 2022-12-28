import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailAddressTest {
    @Test
    public void testGetEmailAddress(){
        EmailAddress email = new EmailAddress("andersgoddard@gmail.com");
        assertEquals("andersgoddard@gmail.com", email.get());
        assertTrue(email.isValid());
    }

    @Test
    public void testDuffEmailAddress(){
        EmailAddress email = new EmailAddress("test@example.com");
        assertFalse(email.isValid());
    }

    @Test
    public void testParseNoisyEmail(){
        EmailAddress email = new EmailAddress("annabel_tino@hotmail.com (Business)");
        assertEquals("annabel_tino@hotmail.com", email.get());
    }
}

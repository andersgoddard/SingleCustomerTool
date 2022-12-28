import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactTest {
    @Test
    public void testBasicContact(){
        Contact contact = new Contact();
        contact.setTitle("Mr");
        contact.setFirstName("Andrew");
        contact.setLastName("Goddard");
        assertEquals("Mr Andrew Goddard", contact.getName());
    }
}

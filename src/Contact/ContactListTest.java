package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.EmailAddress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactListTest {
    ContactList list;

    @BeforeEach
    public void setUp(){
        list = ContactList.getInstance();
    }
    @Test
    public void testEmptyContactList(){
        assertEquals(0, list.size());
        list.clear();
    }

    @Test
    public void testOneContact(){
        ContactInfo info = new ContactInfo();
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        String reference = "2000000";
        Contact contact = Contact.create("Andrew", info, reference);
        assertEquals(1, list.size());
        list.clear();
    }

    @Test
    public void testTwoContacts(){
        ContactInfo info1 = new ContactInfo();
        info1.add(EmailAddress.create("andersgoddard@gmail.com"));
        String reference1 = "2000000";
        Contact contact1 = Contact.create("Andrew", info1, reference1);

        ContactInfo info2 = new ContactInfo();
        info1.add(EmailAddress.create("indiabettsgoddard@outlook.com"));
        String reference2 = "2000001";
        Contact contact2 = Contact.create("India", info2, reference2);

        assertEquals(2, list.size());
        list.clear();
    }
}

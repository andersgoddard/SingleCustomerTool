package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.EmailAddress;
import DatabaseFields.SimpleDatabaseFields;
import DatabaseFields.DatabaseFields;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactListTest {
    ContactList list;
    DatabaseFields fields;

    @BeforeEach
    public void setUp(){
        list = ContactList.getInstance();
        fields = new SimpleDatabaseFields("Mr Andrew Goddard");
        fields.setPrimaryKey("2000000");
        ContactInfo info = new ContactInfo();
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        fields.setContactInfo(info);
    }
    @Test
    public void testEmptyContactList(){
        assertEquals(0, list.size());
    }

    @Test
    public void testOneContact(){
        Contact contact = Contact.create(fields);
        assertEquals(1, list.size());
    }

    @Test
    public void testTwoContacts(){
        Contact contact1 = Contact.create(fields);
        Contact contact2 = Contact.create(fields);
        assertEquals(2, list.size());
    }

    @AfterEach
    public void tearDown(){
        list.clear();
    }
}

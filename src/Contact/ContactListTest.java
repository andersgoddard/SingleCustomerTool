package Contact;

import Associaters.CompanyAssociater;
import ContactInfo.ContactInfo;
import ContactInfo.EmailAddress;
import DatabaseFields.SimpleDatabaseFields;
import DatabaseFields.DatabaseFields;
import Utilities.ContactFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactListTest {
    ContactList contacts;
    DatabaseFields fields;
    CompanyAssociater associater;
    UniqueIdentifierGenerator generator;
    ContactFactory factory;

    @BeforeEach
    public void setUp(){
        contacts = ContactList.getInstance();
        associater = CompanyAssociater.create();
        generator = new BasicUniqueIdentifierGenerator();
        factory = new ContactFactory();
        fields = new SimpleDatabaseFields("Mr Andrew Goddard");
        fields.setPrimaryKey("2000000");
        ContactInfo info = new ContactInfo();
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        fields.setContactInfo(info);
    }
    @Test
    public void testEmptyContactList(){
        assertEquals(0, contacts.size());
    }

    @Test
    public void testOneContact(){
        Contact contact = factory.create(fields, generator, associater, contacts);
        assertEquals(1, contacts.size());
    }

    @Test
    public void testTwoContacts(){
        Contact contact1 = factory.create(fields, generator, associater, contacts);
        Contact contact2 = factory.create(fields, generator, associater, contacts);
        assertEquals(2, contacts.size());
    }

    @AfterEach
    public void tearDown(){
        contacts.clear();
    }
}

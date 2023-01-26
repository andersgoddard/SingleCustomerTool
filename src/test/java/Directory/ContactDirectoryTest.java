package Directory;

import Associaters.CompanyAssociater;
import Contact.BasicUniqueIdentifierGenerator;
import Contact.Contact;
import Contact.ContactFactoryModule;
import Contact.UniqueIdentifierGenerator;
import ContactInfo.ContactInfo;
import ContactInfo.EmailAddress;
import DatabaseFields.SimpleDatabaseFields;
import DatabaseFields.DatabaseFields;
import Utilities.ContactFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactDirectoryTest {
    Directory contacts;
    DatabaseFields fields;
    CompanyAssociater associater;
    UniqueIdentifierGenerator generator;
    ContactFactory factory;

    @BeforeEach
    public void setUp(){
        contacts = ContactDirectory.getInstance();
        associater = CompanyAssociater.create();
        generator = new BasicUniqueIdentifierGenerator();
        ContactInfo info = new ContactInfo();
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        fields = new SimpleDatabaseFields("Mr Andrew Goddard", info, "2000000");
        Injector injector = Guice.createInjector(new ContactFactoryModule());
        factory = injector.getInstance(ContactFactory.class);
    }
    @Test
    public void testEmptyContactList(){
        assertEquals(0, contacts.size());
    }

    @Test
    public void testOneContact(){
        Contact contact = factory.create(fields);
        assertEquals(1, contacts.size());
    }

    @Test
    public void testTwoContacts(){
        Contact contact1 = factory.create(fields);
        Contact contact2 = factory.create(fields);
        assertEquals(2, contacts.size());
    }

    @AfterEach
    public void tearDown(){
        contacts.clear();
    }
}
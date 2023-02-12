package Directory;

import Associaters.CompanyAssociater;
import Contact.BasicUniqueIdentifierGenerator;
import Contact.ContactImpl;
import Contact.ContactFactoryModule;
import Contact.UniqueIdentifierGenerator;
import ContactInfo.ContactInfoImpl;
import ContactInfo.EmailAddress;
import DatabaseFields.DatabaseFieldsImpl;
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
        ContactInfoImpl info = new ContactInfoImpl();
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        fields = new DatabaseFieldsImpl("Mr Andrew Goddard", info, "2000000");
        Injector injector = Guice.createInjector(new ContactFactoryModule());
        factory = injector.getInstance(ContactFactory.class);
    }
    @Test
    public void testEmptyContactList(){
        assertEquals(0, contacts.size());
    }

    @Test
    public void testOneContact(){
        ContactImpl contact = factory.create(fields);
        assertEquals(1, contacts.size());
    }

    @Test
    public void testTwoContacts(){
        ContactImpl contact1 = factory.create(fields);
        ContactImpl contact2 = factory.create(fields);
        assertEquals(2, contacts.size());
    }

    @AfterEach
    public void tearDown(){
        contacts.clear();
    }
}

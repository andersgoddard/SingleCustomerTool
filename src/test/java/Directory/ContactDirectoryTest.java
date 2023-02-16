package Directory;

import Associaters.CompanyAssociaterImpl;
import Contact.Contact;
import Utilities.ContactImplFactoryModule;
import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoImpl;
import ContactInfo.EmailAddress;
import DatabaseFields.DatabaseFieldsImpl;
import DatabaseFields.DatabaseFields;
import Utilities.ContactImplFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactDirectoryTest {
    ContactDirectory contacts;
    DatabaseFields fields;
    CompanyAssociaterImpl associater;
    ContactImplFactory factory;

    @BeforeEach
    public void setUp(){
        contacts = ContactDirectoryImpl.getInstance();
        associater = new CompanyAssociaterImpl(CompanyDirectoryImpl.getInstance());
        ContactInfo info = new ContactInfoImpl();
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        fields = new DatabaseFieldsImpl("Mr Andrew Goddard", info, "2000000");
        Injector injector = Guice.createInjector(new ContactImplFactoryModule(CompanyDirectoryImpl.getInstance()));
        factory = injector.getInstance(ContactImplFactory.class);
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

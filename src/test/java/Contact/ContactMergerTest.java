package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.PhoneNumber;
import ContactInfo.EmailAddress;
import DatabaseFields.DatabaseFields;
import DatabaseFields.SimpleDatabaseFields;
import Directory.Directory;
import Directory.ContactDirectory;
import Utilities.ContactFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactMergerTest {
    Directory contacts;
    ContactFactory factory;
    ContactMerger merger;
    @BeforeEach
    public void setUp(){
        contacts = ContactDirectory.getInstance();
        merger = new ContactMerger();
        Injector injector = Guice.createInjector(new ContactFactoryModule());
        this.factory = injector.getInstance(ContactFactory.class);
    }

    @Test
    public void testMergeTwoContacts(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        DatabaseFields fields1 = new SimpleDatabaseFields("Mr Andrew Goddard", info, null);
        Contact andrew1 = factory.create(fields1);

        ContactInfo info2 = new ContactInfo();
        info2.add(EmailAddress.create("andersgoddard@gmail.com"));
        DatabaseFields fields2 = new SimpleDatabaseFields("Mr A Goddard", info2, null);
        Contact andrew2 = factory.create(fields2);

        assertNotEquals(andrew1.getUniqueIdentifier(), andrew2.getUniqueIdentifier());
        merger.merge(andrew1, andrew2);
        assertEquals(andrew1.getUniqueIdentifier(), andrew2.getUniqueIdentifier());
        assertEquals(1, andrew1.getChildContacts().size());
        assertTrue(andrew1.getChildContacts().contains(andrew2));
    }

    @Test
    public void testMergeThreeContacts(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        DatabaseFields fields1 = new SimpleDatabaseFields("Mr Andrew Goddard", info, null);
        Contact andrew1 = factory.create(fields1);

        ContactInfo info2 = new ContactInfo();
        info2.add(EmailAddress.create("andersgoddard@gmail.com"));
        DatabaseFields fields2 = new SimpleDatabaseFields("Mr A Goddard", info2, null);
        Contact andrew2 = factory.create(fields2);

        ContactInfo info3 = new ContactInfo();
        info3.add(EmailAddress.create("aiandbgoddard@gmail.com"));
        DatabaseFields fields3 = new SimpleDatabaseFields("Mr A Goddard", info3, null);
        Contact andrew3 = factory.create(fields3);

        assertNotEquals(andrew1.getUniqueIdentifier(), andrew2.getUniqueIdentifier());
        assertNotEquals(andrew1.getUniqueIdentifier(), andrew3.getUniqueIdentifier());
        assertNotEquals(andrew2.getUniqueIdentifier(), andrew3.getUniqueIdentifier());

        merger.merge(andrew1, andrew2);
        merger.merge(andrew1, andrew3);
        assertEquals(andrew1.getUniqueIdentifier(), andrew2.getUniqueIdentifier());
        assertEquals(andrew1.getUniqueIdentifier(), andrew3.getUniqueIdentifier());
        assertEquals(andrew2.getUniqueIdentifier(), andrew3.getUniqueIdentifier());
        assertEquals(2, andrew1.getChildContacts().size());
        assertTrue(andrew1.getChildContacts().contains(andrew2));
        assertTrue(andrew1.getChildContacts().contains(andrew3));
    }

    @AfterEach
    public void tearDown(){
        contacts.clear();
    }
}

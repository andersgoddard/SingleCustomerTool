package Contact;

import Directory.CompanyDirectoryImpl;
import Stubs.ContactDirectoryStub;
import Stubs.ContactRegistrarStub;
import Stubs.DatabaseFieldsStub;
import Utilities.ContactImplFactory;
import Utilities.ContactImplFactoryModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactMergerTest {
    ContactImplFactory factory;
    Merger merger;
    ContactRegistrar registrar;

    @BeforeEach
    public void setUp(){
        registrar = new ContactRegistrarStub(new ContactDirectoryStub());
        merger = new ContactMerger();
        Injector injector = Guice.createInjector(new ContactImplFactoryModule(CompanyDirectoryImpl.getInstance()));
        this.factory = injector.getInstance(ContactImplFactory.class);
    }

    @Test
    public void mergeTwoContacts(){
        Contact contact1 = factory.create(new DatabaseFieldsStub(), registrar);
        Contact contact2 = factory.create(new DatabaseFieldsStub(), registrar);
        assertNotEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());

        merger.merge(contact1, contact2);

        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
        assertEquals(1, contact1.getChildContacts().size());
        assertTrue(contact1.getChildContacts().contains(contact2));
    }

    @Test
    public void mergeThreeContacts(){
        Contact contact1 = factory.create(new DatabaseFieldsStub(), registrar);
        Contact contact2 = factory.create(new DatabaseFieldsStub(), registrar);
        Contact contact3 = factory.create(new DatabaseFieldsStub(), registrar);
        assertNotEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
        assertNotEquals(contact1.getUniqueIdentifier(), contact3.getUniqueIdentifier());
        assertNotEquals(contact2.getUniqueIdentifier(), contact3.getUniqueIdentifier());

        merger.merge(contact1, contact2);
        merger.merge(contact1, contact3);

        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
        assertEquals(contact1.getUniqueIdentifier(), contact3.getUniqueIdentifier());
        assertEquals(contact2.getUniqueIdentifier(), contact3.getUniqueIdentifier());
        assertEquals(2, contact1.getChildContacts().size());
        assertTrue(contact1.getChildContacts().contains(contact2));
        assertTrue(contact1.getChildContacts().contains(contact3));
    }
}

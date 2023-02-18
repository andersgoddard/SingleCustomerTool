package Contact;

import Stubs.CompanyDirectoryStub;
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

public class ContactSplitterTest {
    ContactImplFactory factory;
    Merger merger;
    Splitter splitter;
    ContactRegistrar registrar;

    @BeforeEach
    public void setUp(){
        merger = new ContactMerger();
        splitter = new ContactSplitter();
        registrar = new ContactRegistrarStub(new ContactDirectoryStub());
        Injector injector = Guice.createInjector(new ContactImplFactoryModule(new CompanyDirectoryStub()));
        this.factory = injector.getInstance(ContactImplFactory.class);
    }

    @Test
    public void breakUpContact(){
        Contact contact1 = factory.create(new DatabaseFieldsStub(), registrar);
        Contact contact2 = factory.create(new DatabaseFieldsStub(), registrar);
        merger.merge(contact1, contact2);
        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
        assertEquals(1, contact1.getChildContacts().size());
        assertTrue(contact1.getChildContacts().contains(contact2));

        splitter.split(contact1, contact2);

        assertNotEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
        assertEquals(0, contact1.getChildContacts().size());
    }
}

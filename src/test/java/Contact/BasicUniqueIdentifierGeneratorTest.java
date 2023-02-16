package Contact;

import Associaters.CompanyAssociaterImpl;
import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoImpl;
import ContactInfo.PhoneNumber;
import ContactInfo.EmailAddress;
import DatabaseFields.DatabaseFields;
import DatabaseFields.DatabaseFieldsImpl;
import Directory.ContactDirectory;
import Directory.CompanyDirectory;
import Directory.CompanyDirectoryImpl;
import Directory.ContactDirectoryImpl;
import Utilities.ContactImplFactory;
import Utilities.ContactImplFactoryModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BasicUniqueIdentifierGeneratorTest {
    ContactDirectory contacts;
    CompanyDirectory companies;
    DatabaseFields fields;
    UniqueIdentifierGenerator generator = new BasicUniqueIdentifierGenerator();
    CompanyAssociaterImpl associater;
    ContactImplFactory factory;


    @BeforeEach
    public void setUp(){
        contacts = ContactDirectoryImpl.getInstance();
        companies = CompanyDirectoryImpl.getInstance();
        associater = new CompanyAssociaterImpl(CompanyDirectoryImpl.getInstance());
        ContactInfo info = new ContactInfoImpl();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        fields = new DatabaseFieldsImpl("Mr Andrew Goddard", info, null);
        Injector injector = Guice.createInjector(new ContactImplFactoryModule(CompanyDirectoryImpl.getInstance()));
        factory = injector.getInstance(ContactImplFactory.class);
    }

    @Test
    public void testContactHasUniqueIdentifier(){
        Contact contact = factory.create(fields);
        String uniqueIdentifier = generator.getUniqueIdentifierFor(contact);
        assertNotNull(uniqueIdentifier);
    }

    @Test
    public void testSameUniqueIdentifierForSameContact(){
        Contact contact1 = factory.create(fields);
        ContactInfo info = new ContactInfoImpl();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andrewnmngoddard@outlook.com"));
        DatabaseFields fields2 = new DatabaseFieldsImpl("Mr A Goddard", info, null);
        Contact contact2 = factory.create(fields2);
        String uniqueIdentifier1 = generator.getUniqueIdentifierFor(contact1);
        String uniqueIdentifier2 = generator.getUniqueIdentifierFor(contact2);
        assertEquals(uniqueIdentifier1, uniqueIdentifier2);
    }
}

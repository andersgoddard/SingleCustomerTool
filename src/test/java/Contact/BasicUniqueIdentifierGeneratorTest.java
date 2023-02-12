package Contact;

import Associaters.CompanyAssociater;
import ContactInfo.ContactInfoImpl;
import ContactInfo.PhoneNumber;
import ContactInfo.EmailAddress;
import DatabaseFields.DatabaseFields;
import DatabaseFields.DatabaseFieldsImpl;
import Directory.Directory;
import Directory.CompanyDirectory;
import Directory.ContactDirectory;
import Utilities.ContactFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BasicUniqueIdentifierGeneratorTest {
    Directory contacts;
    Directory companies;
    DatabaseFields fields;
    UniqueIdentifierGenerator generator;
    CompanyAssociater associater;
    ContactFactory factory;


    @BeforeEach
    public void setUp(){
        contacts = ContactDirectory.getInstance();
        companies = CompanyDirectory.getInstance();
        associater = CompanyAssociater.create();
        ContactInfoImpl info = new ContactInfoImpl();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        fields = new DatabaseFieldsImpl("Mr Andrew Goddard", info, null);
        generator = new BasicUniqueIdentifierGenerator();
        Injector injector = Guice.createInjector(new ContactFactoryModule());
        factory = injector.getInstance(ContactFactory.class);
    }

    @Test
    public void testContactHasUniqueIdentifier(){
        ContactImpl contact = factory.create(fields);
        String uniqueIdentifier = generator.getUniqueIdentifierFor(contact);
        assertNotNull(uniqueIdentifier);
    }

    @Test
    public void testSameUniqueIdentifierForSameContact(){
        ContactImpl contact1 = factory.create(fields);
        ContactInfoImpl info = new ContactInfoImpl();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andrewnmngoddard@outlook.com"));
        DatabaseFields fields2 = new DatabaseFieldsImpl("Mr A Goddard", info, null);
        ContactImpl contact2 = factory.create(fields2);
        String uniqueIdentifier1 = generator.getUniqueIdentifierFor(contact1);
        String uniqueIdentifier2 = generator.getUniqueIdentifierFor(contact2);
        assertEquals(uniqueIdentifier1, uniqueIdentifier2);
    }
}

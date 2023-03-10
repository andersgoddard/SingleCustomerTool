package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoImpl;
import ContactInfo.PhoneNumber;
import ContactInfo.EmailAddress;

import DatabaseFields.DatabaseFields;
import DatabaseFields.DatabaseFieldsImpl;

import Directory.CompanyDirectoryImpl;
import Directory.ContactDirectory;
import Directory.ContactDirectoryImpl;

import Group.CompanyImpl;

import Utilities.ContactImplFactory;
import Utilities.ContactImplFactoryModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactIntegrationTest {
    ContactImplFactory factory;
    ContactDirectory contacts;
    Injector injector;
    ContactRegistrar registrar;

    @BeforeEach
    public void setUp(){
        contacts = ContactDirectoryImpl.getInstance();
        registrar = new ContactRegistrarImpl(contacts);
        injector = Guice.createInjector(new ContactImplFactoryModule(CompanyDirectoryImpl.getInstance()));
        factory = injector.getInstance(ContactImplFactory.class);
    }

    @Test
    public void testSameUniqueIdentifierForSameContact(){
        ContactInfo info = new ContactInfoImpl();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        String primaryKey = "2000000";
        DatabaseFields fields1 = new DatabaseFieldsImpl("Mr Andrew Goddard", info, primaryKey);
        ContactInfo info2 = new ContactInfoImpl();
        info2.add(PhoneNumber.create("07881266969"));
        info2.add(EmailAddress.create("andrewnmngoddard@outlook.com"));
        DatabaseFields fields2 = new DatabaseFieldsImpl("Mr A Goddard", info, "2000001");

        Contact contact1 = factory.create(fields1, registrar);
        Contact contact2 = factory.create(fields2, registrar);

        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }

    @Test
    public void contactHasEmailDomain(){
        ContactInfo info = new ContactInfoImpl();
        info.add(EmailAddress.create("test@example.com"));
        String primaryKey = "2000000";
        DatabaseFields fields1 = new DatabaseFieldsImpl("Mr Andrew Goddard", info, primaryKey);
        Contact contact = factory.create(fields1, registrar);
        assertTrue(contact.hasEmailDomain("example.com"));
    }

    @Test
    public void testSecondContactAddedToChildContactsOnMerge(){
        ContactInfo info = new ContactInfoImpl();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        String primaryKey = "2000000";
        DatabaseFields fields1 = new DatabaseFieldsImpl("Mr Andrew Goddard", info, primaryKey);
        ContactInfo info2 = new ContactInfoImpl();
        info2.add(EmailAddress.create("indiabettsgoddard@outlook.com"));
        info2.add(PhoneNumber.create("07881266969"));
        DatabaseFields fields2 = new DatabaseFieldsImpl("Mrs India Goddard", info2, "2000002");

        Contact andrew = factory.create(fields1, registrar);
        Contact india = factory.create(fields2, registrar);

        assertEquals(1, andrew.getChildContacts().size());
    }

    @Test
    public void testContactShouldntMergeOnCompanySharedPhoneNumberOnContactCreation(){
        ContactInfo info = new ContactInfoImpl();
        info.add(PhoneNumber.create("02078932000"));
        DatabaseFields fields1 = new DatabaseFieldsImpl("Mr Andrew Goddard", info, null);
        DatabaseFields fields2 = new DatabaseFieldsImpl("Mrs India Goddard", info, null);
        CompanyImpl company = CompanyImpl.create("Example Company");
        company.setSharedContactInfo("02078932000");
        Contact contact1 = factory.create(fields1, registrar);
        Contact contact2 = factory.create(fields2, registrar);
        assertNotEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }

    @Test
    public void testContactShouldMergeOnNameAndCompanySharedContactInfo(){
        ContactInfo info = new ContactInfoImpl();
        info.add(PhoneNumber.create("02078932000"));
        DatabaseFields fields1 = new DatabaseFieldsImpl("Mr Andrew Goddard", info, null);
        DatabaseFields fields2 = new DatabaseFieldsImpl("Mr Andrew Goddard", info, null);
        CompanyImpl company = CompanyImpl.create("Example Company");

        company.setSharedContactInfo("02078932000");
        Contact contact1 = factory.create(fields1, registrar);
        Contact contact2 = factory.create(fields2, registrar);
        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }


    @Test
    public void testMergedContactShouldSplitOnCompanySharedPhoneNumberSet(){
        ContactInfo info = new ContactInfoImpl();
        info.add(PhoneNumber.create("02078932000"));
        DatabaseFields fields1 = new DatabaseFieldsImpl("Mr Andrew Goddard", info, null);
        DatabaseFields fields2 = new DatabaseFieldsImpl("Mrs India Goddard", info, null);

        Contact contact1 = factory.create(fields1, registrar);
        Contact contact2 = factory.create(fields2, registrar);
        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());

        CompanyImpl company = CompanyImpl.create("Example Company");
        company.setSharedContactInfo("02078932000");
        assertNotEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }

    @Test
    public void testMergeThreeContacts(){
        ContactInfo info = new ContactInfoImpl();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        String primaryKey = "2000000";
        DatabaseFields fields1 = new DatabaseFieldsImpl("Mr Andrew Goddard", info, primaryKey);

        ContactInfo info2 = new ContactInfoImpl();
        info2.add(PhoneNumber.create("07881266969"));
        DatabaseFields fields2 = new DatabaseFieldsImpl("Mr Goddard", info2, "2000002");

        ContactInfo info3 = new ContactInfoImpl();
        info3.add(PhoneNumber.create("07881266969"));
        info3.add(EmailAddress.create("andrewnmngoddard@outlook.com"));
        DatabaseFields fields3 = new DatabaseFieldsImpl("Mr Goddard", info3, "2000003");

        Contact contact1 = factory.create(fields1, registrar);
        Contact contact2 = factory.create(fields2, registrar);
        Contact contact3 = factory.create(fields3, registrar);

        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
        assertEquals(contact1.getUniqueIdentifier(), contact3.getUniqueIdentifier());
        assertEquals(contact2.getUniqueIdentifier(), contact3.getUniqueIdentifier());
        assertEquals(2, contact1.getChildContacts().size());
    }

    @Test
    public void testMergeThreeOfFourContacts(){
        ContactInfo info = new ContactInfoImpl();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        String primaryKey = "2000000";
        DatabaseFields fields1 = new DatabaseFieldsImpl("Mr Andrew Goddard", info, primaryKey);
        ContactInfo info2 = new ContactInfoImpl();
        info2.add(PhoneNumber.create("07881266969"));
        DatabaseFields fields2 = new DatabaseFieldsImpl("Mr Goddard", info2, "2000002");
        ContactInfo info3 = new ContactInfoImpl();
        info3.add(EmailAddress.create("ag@hotmail.com"));
        DatabaseFields fields3 = new DatabaseFieldsImpl("Mr Goddard", info3, "2000003");
        ContactInfo info4 = new ContactInfoImpl();
        info4.add(PhoneNumber.create("07881266969"));
        info4.add(EmailAddress.create("andrewnmngoddard@outlook.com"));
        DatabaseFields fields4 = new DatabaseFieldsImpl("Mr Goddard", info4, "2000004");

        Contact contact1 = factory.create(fields1, registrar);
        Contact contact2 = factory.create(fields2, registrar);
        Contact contact3 = factory.create(fields3, registrar);
        Contact contact4 = factory.create(fields4, registrar);

        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
        assertNotEquals(contact1.getUniqueIdentifier(), contact3.getUniqueIdentifier());
        assertEquals(contact1.getUniqueIdentifier(), contact4.getUniqueIdentifier());
        assertNotEquals(contact2.getUniqueIdentifier(), contact3.getUniqueIdentifier());
        assertEquals(contact2.getUniqueIdentifier(), contact4.getUniqueIdentifier());
        assertNotEquals(contact3.getUniqueIdentifier(), contact4.getUniqueIdentifier());
        assertEquals(2, contact1.getChildContacts().size());
    }

    @AfterEach
    public void tearDown(){
        contacts.clear();
    }
}

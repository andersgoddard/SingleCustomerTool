package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.EmailAddress;
import ContactInfo.PhoneNumber;
import DatabaseFields.SimpleDatabaseFields;
import DatabaseFields.DatabaseFields;
import Directory.Directory;
import Directory.ContactDirectory;
import Group.Company;
import Utilities.ContactFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.inject.Guice;
import com.google.inject.Injector;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {
    ContactFactory factory;
    Directory contacts;
    Injector injector;


    @BeforeEach
    public void setUp(){
        contacts = ContactDirectory.getInstance();
        injector = Guice.createInjector(new ContactFactoryModule());
        factory = injector.getInstance(ContactFactory.class);
    }

    @Test
    public void testBasicContact(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        String primaryKey = "2000000";
        DatabaseFields fields = new SimpleDatabaseFields("Mr Andrew Goddard", info, primaryKey);

        Contact contact = factory.create(fields);

        assertEquals("Mr Andrew Goddard", contact.getDatabaseFields().getName());
        assertEquals("2000000", contact.getDatabaseFields().getPrimaryKey());
    }

    @Test
    public void testContactInfo(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        String primaryKey = "2000000";
        DatabaseFields fields = new SimpleDatabaseFields("Mr Andrew Goddard", info, primaryKey);

        Contact contact = factory.create(fields);

        assertTrue(contact.hasContactInfoItem(EmailAddress.create("andersgoddard@gmail.com")));
    }

    @Test
    public void testContactHasUniqueIdentifier(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        String primaryKey = "2000000";
        DatabaseFields fields = new SimpleDatabaseFields("Mr Andrew Goddard", info, primaryKey);

        Contact contact = factory.create(fields);

        assertNotNull(contact.getUniqueIdentifier());
    }

    @Test
    public void testSameUniqueIdentifierForSameContact(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        String primaryKey = "2000000";
        DatabaseFields fields1 = new SimpleDatabaseFields("Mr Andrew Goddard", info, primaryKey);
        ContactInfo info2 = new ContactInfo();
        info2.add(PhoneNumber.create("07881266969"));
        info2.add(EmailAddress.create("andrewnmngoddard@outlook.com"));
        DatabaseFields fields2 = new SimpleDatabaseFields("Mr A Goddard", info, "2000001");

        Contact contact1 = factory.create(fields1);
        Contact contact2 = factory.create(fields2);

        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }

    @Test
    public void testSecondContactAddedToChildContactsOnMerge(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        String primaryKey = "2000000";
        DatabaseFields fields1 = new SimpleDatabaseFields("Mr Andrew Goddard", info, primaryKey);
        ContactInfo info2 = new ContactInfo();
        info2.add(EmailAddress.create("indiabettsgoddard@outlook.com"));
        info2.add(PhoneNumber.create("07881266969"));
        DatabaseFields fields2 = new SimpleDatabaseFields("Mrs India Goddard", info2, "2000002");

        Contact andrew = factory.create(fields1);
        Contact india = factory.create(fields2);

        assertEquals(1, andrew.getChildContacts().size());
    }

    @Test
    public void testContactShouldntMergeOnCompanySharedPhoneNumberOnContactCreation(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("02078932000"));
        DatabaseFields fields1 = new SimpleDatabaseFields("Mr Andrew Goddard", info, null);
        DatabaseFields fields2 = new SimpleDatabaseFields("Mrs India Goddard", info, null);
        Company company = Company.create("Example Company");
        company.setSharedContactInfo("02078932000");
        Contact contact1 = factory.create(fields1);
        Contact contact2 = factory.create(fields2);
        assertNotEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }

    @Test
    public void testContactShouldMergeOnNameAndCompanySharedContactInfo(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("02078932000"));
        DatabaseFields fields1 = new SimpleDatabaseFields("Mr Andrew Goddard", info, null);
        DatabaseFields fields2 = new SimpleDatabaseFields("Mr Andrew Goddard", info, null);
        Company company = Company.create("Example Company");

        company.setSharedContactInfo("02078932000");
        Contact contact1 = factory.create(fields1);
        Contact contact2 = factory.create(fields2);
        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }


    @Test
    public void testMergedContactShouldSplitOnCompanySharedPhoneNumberSet(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("02078932000"));
        DatabaseFields fields1 = new SimpleDatabaseFields("Mr Andrew Goddard", info, null);
        DatabaseFields fields2 = new SimpleDatabaseFields("Mrs India Goddard", info, null);

        Contact contact1 = factory.create(fields1);
        Contact contact2 = factory.create(fields2);
        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());

        Company company = Company.create("Example Company");
        company.setSharedContactInfo("02078932000");
        assertNotEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }

    @Test
    public void testMergeThreeContacts(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        String primaryKey = "2000000";
        DatabaseFields fields1 = new SimpleDatabaseFields("Mr Andrew Goddard", info, primaryKey);

        ContactInfo info2 = new ContactInfo();
        info2.add(PhoneNumber.create("07881266969"));
        DatabaseFields fields2 = new SimpleDatabaseFields("Mr Goddard", info2, "2000002");

        ContactInfo info3 = new ContactInfo();
        info3.add(PhoneNumber.create("07881266969"));
        info3.add(EmailAddress.create("andrewnmngoddard@outlook.com"));
        DatabaseFields fields3 = new SimpleDatabaseFields("Mr Goddard", info3, "2000003");

        Contact contact1 = factory.create(fields1);
        Contact contact2 = factory.create(fields2);
        Contact contact3 = factory.create(fields3);

        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
        assertEquals(contact1.getUniqueIdentifier(), contact3.getUniqueIdentifier());
        assertEquals(contact2.getUniqueIdentifier(), contact3.getUniqueIdentifier());
        assertEquals(2, contact1.getChildContacts().size());
    }

    @Test
    public void testMergeThreeOfFourContacts(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        String primaryKey = "2000000";
        DatabaseFields fields1 = new SimpleDatabaseFields("Mr Andrew Goddard", info, primaryKey);
        ContactInfo info2 = new ContactInfo();
        info2.add(PhoneNumber.create("07881266969"));
        DatabaseFields fields2 = new SimpleDatabaseFields("Mr Goddard", info2, "2000002");
        ContactInfo info3 = new ContactInfo();
        info3.add(EmailAddress.create("ag@hotmail.com"));
        DatabaseFields fields3 = new SimpleDatabaseFields("Mr Goddard", info3, "2000003");
        ContactInfo info4 = new ContactInfo();
        info4.add(PhoneNumber.create("07881266969"));
        info4.add(EmailAddress.create("andrewnmngoddard@outlook.com"));
        DatabaseFields fields4 = new SimpleDatabaseFields("Mr Goddard", info4, "2000004");

        Contact contact1 = factory.create(fields1);
        Contact contact2 = factory.create(fields2);
        Contact contact3 = factory.create(fields3);
        Contact contact4 = factory.create(fields4);

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

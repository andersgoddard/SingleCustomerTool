package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.EmailAddress;
import ContactInfo.PhoneNumber;
import DatabaseFields.SimpleDatabaseFields;
import DatabaseFields.DatabaseFields;
import Group.Company;
import Utilities.ContactFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.inject.Guice;
import com.google.inject.Injector;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {
    DatabaseFields fields;
    ContactFactory factory;
    ContactList contacts;


    @BeforeEach
    public void setUp(){
        contacts = ContactList.getInstance();
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        String primaryKey = "2000000";
        fields = new SimpleDatabaseFields("Mr Andrew Goddard", info, primaryKey);
        Injector injector = Guice.createInjector(new ContactFactoryModule());
        factory = injector.getInstance(ContactFactory.class);
    }

    @Test
    public void testBasicContact(){
        Contact contact = factory.create(fields);
        assertEquals("Mr Andrew Goddard", contact.getDatabaseFields().getName());
        assertEquals("2000000", contact.getDatabaseFields().getPrimaryKey());
    }

    @Test
    public void testContactInfo(){
        Contact contact = factory.create(fields);
        assertTrue(contact.hasContactInfoItem(EmailAddress.create("andersgoddard@gmail.com")));
    }

    @Test
    public void testContactHasUniqueIdentifier(){
        Contact contact = factory.create(fields);
        assertNotNull(contact.getUniqueIdentifier());
    }

    @Test
    public void testSameUniqueIdentifierForSameContact(){
        Contact contact1 = factory.create(fields);
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andrewnmngoddard@outlook.com"));
        DatabaseFields fields2 = new SimpleDatabaseFields("Mr A Goddard", info, "2000001");
        Contact contact2 = factory.create(fields2);
        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }

    @Test
    public void testSecondContactAddedToChildContactsOnMerge(){
        Contact contact = factory.create(fields);
        ContactInfo info2 = new ContactInfo();
        info2.add(EmailAddress.create("indiabettsgoddard@outlook.com"));
        info2.add(PhoneNumber.create("07881266969"));
        DatabaseFields fields2 = new SimpleDatabaseFields("Mrs India Goddard", info2, "2000002");
        Contact india = factory.create(fields2);

        assertEquals(1, contact.getChildContacts().size());
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

    @AfterEach
    public void tearDown(){
        contacts.clear();
    }
}

package Contact;

import Associaters.CompanyAssociater;
import ContactInfo.ContactInfo;
import ContactInfo.EmailAddress;
import ContactInfo.PhoneNumber;
import DatabaseFields.SimpleDatabaseFields;
import DatabaseFields.DatabaseFields;
import Group.Company;
import Group.CompanyList;
import Utilities.ContactFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {
    ContactList contacts;
    CompanyList companies;
    DatabaseFields fields;
    CompanyAssociater associater;
    UniqueIdentifierGenerator generator;
    ContactFactory factory;


    @BeforeEach
    public void setUp(){
        contacts = ContactList.getInstance();
        companies = CompanyList.getInstance();
        associater = CompanyAssociater.create();
        generator = new BasicUniqueIdentifierGenerator();
        factory = new ContactFactory();
        fields = new SimpleDatabaseFields("Mr Andrew Goddard");
        fields.setPrimaryKey("2000000");
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        fields.setContactInfo(info);
    }

    @Test
    public void testBasicContact(){
        Contact contact = factory.create(fields, generator, associater, contacts);
        assertEquals("Mr Andrew Goddard", contact.getDatabaseFields().getName());
        assertEquals("2000000", contact.getDatabaseFields().getPrimaryKey());
    }

    @Test
    public void testContactInfo(){
        Contact contact = factory.create(fields, generator, associater, contacts);
        assertTrue(contact.hasContactInfoItem(EmailAddress.create("andersgoddard@gmail.com")));
    }

    @Test
    public void testContactAddedToContactList(){
        Contact contact = factory.create(fields, generator, associater, contacts);
        assertEquals(1, contacts.size());
    }

    @Test
    public void testContactHasUniqueIdentifier(){
        Contact contact = factory.create(fields, generator, associater, contacts);
        assertNotNull(contact.getUniqueIdentifier());
    }

    @Test
    public void testSameUniqueIdentifierForSameContact(){
        Contact contact1 = factory.create(fields, generator, associater, contacts);
        DatabaseFields fields2 = new SimpleDatabaseFields("Mr A Goddard");
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andrewnmngoddard@outlook.com"));
        fields2.setContactInfo(info);
        fields2.setPrimaryKey("2000001");
        Contact contact2 = factory.create(fields2, generator, associater, contacts);
        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }

    @Test
    public void testSecondContactAddedToChildContactsOnMerge(){
        Contact contact = factory.create(fields, generator, associater, contacts);
        DatabaseFields fields2 = new SimpleDatabaseFields("Mrs India Goddard");
        ContactInfo info2 = new ContactInfo();
        info2.add(EmailAddress.create("indiabettsgoddard@outlook.com"));
        info2.add(PhoneNumber.create("07881266969"));
        fields2.setContactInfo(info2);
        Contact india = factory.create(fields2, generator, associater, contacts);

        assertEquals(1, contact.getChildContacts().size());
    }

    @Test
    public void testContactShouldntMergeOnCompanySharedPhoneNumberOnContactCreation(){
        DatabaseFields fields1 = new SimpleDatabaseFields("Mr Andrew Goddard");
        DatabaseFields fields2 = new SimpleDatabaseFields("Mrs India Goddard");
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("02078932000"));
        fields1.setContactInfo(info);
        fields2.setContactInfo(info);
        Company company = Company.create("Example Company");
        company.setSharedContactInfo("02078932000");
        Contact contact1 = factory.create(fields1, generator, associater, contacts);
        Contact contact2 = factory.create(fields2, generator, associater, contacts);
        assertNotEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }

    @Test
    public void testContactShouldMergeOnNameAndCompanySharedContactInfo(){
        DatabaseFields fields1 = new SimpleDatabaseFields("Mr Andrew Goddard");
        DatabaseFields fields2 = new SimpleDatabaseFields("Mr Andrew Goddard");
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("02078932000"));
        fields1.setContactInfo(info);
        fields2.setContactInfo(info);
        Company company = Company.create("Example Company");

        company.setSharedContactInfo("02078932000");
        Contact contact1 = factory.create(fields1, generator, associater, contacts);
        Contact contact2 = factory.create(fields2, generator, associater, contacts);
        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }


    @Test
    public void testMergedContactShouldSplitOnCompanySharedPhoneNumberSet(){
        DatabaseFields fields1 = new SimpleDatabaseFields("Mr Andrew Goddard");
        DatabaseFields fields2 = new SimpleDatabaseFields("Mrs India Goddard");
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("02078932000"));
        fields1.setContactInfo(info);
        fields2.setContactInfo(info);

        Contact contact1 = factory.create(fields1, generator, associater, contacts);
        Contact contact2 = factory.create(fields2, generator, associater, contacts);
        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());

        Company company = Company.create("Example Company");
        company.setSharedContactInfo("02078932000");
        assertNotEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }

    @AfterEach
    public void tearDown(){
        contacts.clear();
        companies.clear();
    }
}

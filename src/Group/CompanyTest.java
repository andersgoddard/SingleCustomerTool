package Group;

import Associaters.CompanyAssociater;
import Contact.ContactList;
import Contact.Contact;
import Contact.UniqueIdentifierGenerator;
import Contact.BasicUniqueIdentifierGenerator;
import ContactInfo.ContactInfo;
import ContactInfo.EmailAddress;
import ContactInfo.PhoneNumber;
import DatabaseFields.DatabaseFields;
import DatabaseFields.SimpleDatabaseFields;
import Utilities.ContactFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest {
    Company company;
    ContactList contacts;
    CompanyList companies;
    DatabaseFields fields;
    UniqueIdentifierGenerator generator;
    CompanyAssociater associater;
    ContactFactory factory;


    @BeforeEach
    public void setUp(){
        company = Company.create("Example Company");
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
    public void testGetCompanyName(){
        assertEquals("Example Company", company.getName());
    }

    @Test
    public void testCompanyHasUID(){
        assertNotNull(company.getUniqueIdentifier());
    }

    @Test
    public void testCompanyEmailDomain(){
        company.setEmailDomain("exampleco.com");
        assertEquals("exampleco.com", company.getEmailDomain());
    }

    @Test
    public void testContactsAssociatedWhenEmailDomainSet(){
        DatabaseFields andrewFields = new SimpleDatabaseFields("Andrew");
        DatabaseFields indiaFields = new SimpleDatabaseFields("India");
        ContactInfo info1 = new ContactInfo();
        ContactInfo info2 = new ContactInfo();
        info1.add(EmailAddress.create("andrew@exampleco.com"));
        info2.add(EmailAddress.create("india@exampleco.com"));
        andrewFields.setContactInfo(info1);
        indiaFields.setContactInfo(info2);
        Contact andrew = factory.create(andrewFields, generator, associater, contacts);
        Contact india = factory.create(indiaFields, generator, associater, contacts);
        company.setEmailDomain("exampleco.com");
        assertEquals(andrew.getCompanyId(), india.getCompanyId());
    }

    @Test
    public void testCompanyCreatedWithEmailDomain(){
        Company company2 = Company.create("Another Example Company", "anotherexampleco.com");
        assertEquals("Another Example Company", company2.getName());
        assertEquals("anotherexampleco.com", company2.getEmailDomain());
        assertNotNull(company2.getUniqueIdentifier());
    }

    @Test
    public void testContactsAssociatedWhenCompanyCreatedWithDomain(){
        DatabaseFields andrewFields = new SimpleDatabaseFields("Andrew");
        DatabaseFields indiaFields = new SimpleDatabaseFields("India");
        ContactInfo info1 = new ContactInfo();
        ContactInfo info2 = new ContactInfo();
        info1.add(EmailAddress.create("andrew@anotherexampleco.com"));
        info2.add(EmailAddress.create("india@anotherexampleco.com"));
        andrewFields.setContactInfo(info1);
        indiaFields.setContactInfo(info2);

        Contact andrew = factory.create(andrewFields, generator, associater, contacts);
        Contact india = factory.create(indiaFields, generator, associater, contacts);
        Company exampleCo = Company.create("Another Example Company", "anotherexampleco.com");
        assertEquals(andrew.getCompanyId(), india.getCompanyId());
    }

    @Test
    public void testCompanyAssociatedWhenContactCreated(){
        company.setEmailDomain("exampleco.com");
        DatabaseFields andrewFields = new SimpleDatabaseFields("Andrew");
        DatabaseFields indiaFields = new SimpleDatabaseFields("India");
        ContactInfo info1 = new ContactInfo();
        ContactInfo info2 = new ContactInfo();
        info1.add(EmailAddress.create("andrew@exampleco.com"));
        info2.add(EmailAddress.create("india@exampleco.com"));
        andrewFields.setContactInfo(info1);
        indiaFields.setContactInfo(info2);

        Contact andrew = factory.create(andrewFields, generator, associater, contacts);
        Contact india = factory.create(indiaFields, generator, associater, contacts);
        assertNotNull(andrew.getCompanyId());
        assertEquals(andrew.getCompanyId(), india.getCompanyId());
    }

    @Test
    public void testCompanyPhoneNumbersAddedIndividually(){
        company.setSharedContactInfo("02078930000");
        assertEquals("02078930000", company.getSharedContactInfo().getItems().get(0).get());
    }

    @Test
    public void testCompanyPhoneNumbersAddedAsList(){
        ContactInfo numbers = new ContactInfo();
        numbers.add(PhoneNumber.create("02078930000"));
        numbers.add(PhoneNumber.create("02088882000"));
        company.setSharedContactInfo(numbers);
        assertEquals("02078930000", company.getSharedContactInfo().getItems().get(0).get());
        assertEquals("02088882000", company.getSharedContactInfo().getItems().get(1).get());
    }

    @Test
    public void testCompanyAssociatedWithContactBySharedPhoneNumberOnCompanyCreation(){
        DatabaseFields fields = new SimpleDatabaseFields("Andrew");
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("02070002000"));
        fields.setContactInfo(info);
        Contact andrew = factory.create(fields, generator, associater, contacts);
        company.setSharedContactInfo("02070002000");
        assertEquals(company.getUniqueIdentifier(), andrew.getCompanyId());
    }

    @Test
    public void testContactAssociatedWithCompanyOnContactCreation(){
        company.setSharedContactInfo("02070002000");
        DatabaseFields fields = new SimpleDatabaseFields("Andrew");
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("02070002000"));
        fields.setContactInfo(info);
        Contact andrew = factory.create(fields, generator, associater, contacts);
        assertEquals(company.getUniqueIdentifier(), andrew.getCompanyId());
    }

    @Test
    public void testContactAssociatedBySharedEmailAddress(){
        company.setSharedContactInfo("info@examplecompany.com");
        DatabaseFields fields = new SimpleDatabaseFields("Andrew");
        ContactInfo info = new ContactInfo();
        info.add(EmailAddress.create("info@examplecompany.com"));
        fields.setContactInfo(info);
        Contact andrew = factory.create(fields, generator, associater, contacts);
        assertEquals(company.getUniqueIdentifier(), andrew.getCompanyId());
    }

    @AfterEach
    public void tearDown(){
        companies.clear();
        contacts.clear();
    }
}

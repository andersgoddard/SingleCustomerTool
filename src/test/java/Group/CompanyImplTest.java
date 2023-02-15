package Group;

import Associaters.CompanyAssociaterImpl;
import Directory.CompanyDirectory;
import Directory.ContactDirectory;
import Directory.CompanyDirectoryImpl;
import Directory.ContactDirectoryImpl;
import Contact.ContactImpl;
import Contact.UniqueIdentifierGenerator;
import Contact.ContactFactoryModule;
import Contact.BasicUniqueIdentifierGenerator;
import ContactInfo.ContactInfoImpl;
import ContactInfo.EmailAddress;
import ContactInfo.PhoneNumber;
import DatabaseFields.DatabaseFields;
import DatabaseFields.DatabaseFieldsImpl;
import Utilities.ContactImplFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyImplTest {
    CompanyImpl company;
    ContactDirectory contacts;
    CompanyDirectory companies;
    DatabaseFields fields;
    UniqueIdentifierGenerator generator = new BasicUniqueIdentifierGenerator();
    CompanyAssociaterImpl associater;
    ContactImplFactory factory;


    @BeforeEach
    public void setUp(){
        company = CompanyImpl.create("Example Company");
        contacts = ContactDirectoryImpl.getInstance();
        companies = CompanyDirectoryImpl.getInstance();
        associater = CompanyAssociaterImpl.create();
        ContactInfoImpl info = new ContactInfoImpl();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        fields = new DatabaseFieldsImpl("Mr Andrew Goddard", info, "2000000");
        Injector injector = Guice.createInjector(new ContactFactoryModule());
        factory = injector.getInstance(ContactImplFactory.class);
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
        ContactInfoImpl info1 = new ContactInfoImpl();
        ContactInfoImpl info2 = new ContactInfoImpl();
        info1.add(EmailAddress.create("andrew@exampleco.com"));
        info2.add(EmailAddress.create("india@exampleco.com"));
        DatabaseFields andrewFields = new DatabaseFieldsImpl("Andrew", info1, null);
        DatabaseFields indiaFields = new DatabaseFieldsImpl("India", info2, null);
        ContactImpl andrew = factory.create(andrewFields);
        ContactImpl india = factory.create(indiaFields);
        company.setEmailDomain("exampleco.com");
        assertEquals(andrew.getCompanyId(), india.getCompanyId());
    }

    @Test
    public void testCompanyCreatedWithEmailDomain(){
        CompanyImpl company2 = CompanyImpl.create("Another Example Company", "anotherexampleco.com");
        assertEquals("Another Example Company", company2.getName());
        assertEquals("anotherexampleco.com", company2.getEmailDomain());
        assertNotNull(company2.getUniqueIdentifier());
    }

    @Test
    public void testContactsAssociatedWhenCompanyCreatedWithDomain(){
        ContactInfoImpl info1 = new ContactInfoImpl();
        ContactInfoImpl info2 = new ContactInfoImpl();
        info1.add(EmailAddress.create("andrew@anotherexampleco.com"));
        info2.add(EmailAddress.create("india@anotherexampleco.com"));
        DatabaseFields andrewFields = new DatabaseFieldsImpl("Andrew", info1, null);
        DatabaseFields indiaFields = new DatabaseFieldsImpl("India", info2, null);

        ContactImpl andrew = factory.create(andrewFields);
        ContactImpl india = factory.create(indiaFields);
        CompanyImpl exampleCo = CompanyImpl.create("Another Example Company", "anotherexampleco.com");
        assertEquals(andrew.getCompanyId(), india.getCompanyId());
    }

    @Test
    public void testCompanyAssociatedWhenContactCreated(){
        company.setEmailDomain("exampleco.com");
        ContactInfoImpl info1 = new ContactInfoImpl();
        ContactInfoImpl info2 = new ContactInfoImpl();
        info1.add(EmailAddress.create("andrew@exampleco.com"));
        info2.add(EmailAddress.create("india@exampleco.com"));
        DatabaseFields andrewFields = new DatabaseFieldsImpl("Andrew", info1, null);
        DatabaseFields indiaFields = new DatabaseFieldsImpl("India", info2, null);

        ContactImpl andrew = factory.create(andrewFields);
        ContactImpl india = factory.create(indiaFields);
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
        ContactInfoImpl numbers = new ContactInfoImpl();
        numbers.add(PhoneNumber.create("02078930000"));
        numbers.add(PhoneNumber.create("02088882000"));
        company.setSharedContactInfo(numbers);
        assertEquals("02078930000", company.getSharedContactInfo().getItems().get(0).get());
        assertEquals("02088882000", company.getSharedContactInfo().getItems().get(1).get());
    }

    @Test
    public void testCompanyAssociatedWithContactBySharedPhoneNumberOnCompanyCreation(){
        ContactInfoImpl info = new ContactInfoImpl();
        info.add(PhoneNumber.create("02070002000"));
        DatabaseFields fields = new DatabaseFieldsImpl("Andrew", info, null);
        ContactImpl andrew = factory.create(fields);
        company.setSharedContactInfo("02070002000");
        assertEquals(company.getUniqueIdentifier(), andrew.getCompanyId());
    }

    @Test
    public void testContactAssociatedWithCompanyOnContactCreation(){
        company.setSharedContactInfo("02070002000");
        ContactInfoImpl info = new ContactInfoImpl();
        info.add(PhoneNumber.create("02070002000"));
        DatabaseFields fields = new DatabaseFieldsImpl("Andrew", info, null);
        ContactImpl andrew = factory.create(fields);
        assertEquals(company.getUniqueIdentifier(), andrew.getCompanyId());
    }

    @Test
    public void testContactAssociatedBySharedEmailAddress(){
        company.setSharedContactInfo("info@examplecompany.com");
        ContactInfoImpl info = new ContactInfoImpl();
        info.add(EmailAddress.create("info@examplecompany.com"));
        DatabaseFields fields = new DatabaseFieldsImpl("Andrew", info, null);
        ContactImpl andrew = factory.create(fields);
        assertEquals(company.getUniqueIdentifier(), andrew.getCompanyId());
    }

    @AfterEach
    public void tearDown(){
        companies.clear();
        contacts.clear();
    }
}

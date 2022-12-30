package Group;

import Contact.ContactList;
import Contact.Contact;
import ContactInfo.ContactInfo;
import ContactInfo.EmailAddress;
import ContactInfo.PhoneNumber;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest {
    Company company;
    ContactList contacts;
    CompanyList companies;

    @BeforeEach
    public void setUp(){
        company = Company.create("Example Company");
        contacts = ContactList.getInstance();
        companies = CompanyList.getInstance();
    }

    @Test
    public void testGetCompanyName(){
        assertEquals("Example Company", company.get());
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
        ContactInfo info1 = new ContactInfo();
        ContactInfo info2 = new ContactInfo();
        info1.add(EmailAddress.create("andrew@exampleco.com"));
        info2.add(EmailAddress.create("india@exampleco.com"));
        Contact andrew = Contact.create("Andrew", info1, "2000");
        Contact india = Contact.create("India", info2, "2001");
        company.setEmailDomain("exampleco.com");
        assertEquals(andrew.getCompanyId(), india.getCompanyId());
    }

    @Test
    public void testCompanyCreatedWithEmailDomain(){
        Company company2 = Company.create("Another Example Company", "anotherexampleco.com");
        assertEquals("Another Example Company", company2.get());
        assertEquals("anotherexampleco.com", company2.getEmailDomain());
        assertNotNull(company2.getUniqueIdentifier());
    }

    @Test
    public void testContactsAssociatedWhenCompanyCreatedWithDomain(){
        ContactInfo info1 = new ContactInfo();
        ContactInfo info2 = new ContactInfo();
        info1.add(EmailAddress.create("andrew@anotherexampleco.com"));
        info2.add(EmailAddress.create("india@anotherexampleco.com"));
        Contact andrew = Contact.create("Andrew", info1, "2000");
        Contact india = Contact.create("India", info2, "2001");
        Company exampleCo = Company.create("Another Example Company", "anotherexampleco.com");
        assertEquals(andrew.getCompanyId(), india.getCompanyId());
    }

    @Test
    public void testCompanyAssociatedWhenContactCreated(){
        company.setEmailDomain("exampleco.com");
        ContactInfo info1 = new ContactInfo();
        ContactInfo info2 = new ContactInfo();
        info1.add(EmailAddress.create("andrew@exampleco.com"));
        info2.add(EmailAddress.create("india@exampleco.com"));
        Contact andrew = Contact.create("Andrew", info1, "2000");
        Contact india = Contact.create("India", info2, "2001");
        assertNotNull(andrew.getCompanyId());
        assertEquals(andrew.getCompanyId(), india.getCompanyId());
    }

    @Test
    public void testCompanyPhoneNumbersAddedIndividually(){
        company.setSharedPhoneNumbers("02078930000");
        assertEquals("02078930000", company.getSharedPhoneNumbers().get(0).get());
    }

    @Test
    public void testCompanyPhoneNumbersAddedAsList(){
        List<PhoneNumber> numbers = new ArrayList<>();
        numbers.add(PhoneNumber.create("02078930000"));
        numbers.add(PhoneNumber.create("02088882000"));
        company.setSharedPhoneNumbers(numbers);
        assertEquals("02078930000", company.getSharedPhoneNumbers().get(0).get());
        assertEquals("02088882000", company.getSharedPhoneNumbers().get(1).get());
    }

    @Test
    public void testCompanyAssociatedWithContactBySharedPhoneNumberOnCompanyCreation(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("02070002000"));
        Contact andrew = Contact.create("Andrew", info, "000");
        company.setSharedPhoneNumbers("02070002000");
        assertEquals(company.companyId, andrew.getCompanyId());
    }

    @Test
    public void testContactAssociatedWithCompanyOnContactCreation(){
        company.setSharedPhoneNumbers("02070002000");
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("02070002000"));
        Contact andrew = Contact.create("Andrew", info, "000");
        assertEquals(company.companyId, andrew.getCompanyId());
    }

    @AfterEach
    public void tearDown(){
        companies.clear();
        contacts.clear();
    }
}

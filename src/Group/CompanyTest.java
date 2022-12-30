package Group;

import Contact.ContactList;
import Contact.Contact;
import ContactInfo.ContactInfo;
import ContactInfo.EmailAddress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CompanyTest {
    Company company;

    @BeforeEach
    public void setUp(){
        company = Company.create("Example Company");
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
    public void testContactsAssociatedWhenCompanyCreated(){
        ContactList list = ContactList.getInstance();

        ContactInfo info1 = new ContactInfo();
        ContactInfo info2 = new ContactInfo();
        info1.add(EmailAddress.create("andrew@residentialland.com"));
        info2.add(EmailAddress.create("india@residentialland.com"));
        Contact andrew = Contact.create("Andrew", info1, "2000");
        Contact india = Contact.create("India", info2, "2001");

        Company resiland = Company.create("Residential Land");
        resiland.setEmailDomain("residentialland.com");
        assertEquals(andrew.getCompanyId(), india.getCompanyId());

        list.clear();
    }

    @Test
    public void testCompanyCreatedWithEmailDomain(){
        Company company2 = Company.create("Residential Land", "residentialland.com");
        assertEquals("Residential Land", company2.get());
        assertEquals("residentialland.com", company2.getEmailDomain());
        assertNotNull(company2.getUniqueIdentifier());
    }
}

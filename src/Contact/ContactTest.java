package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.EmailAddress;
import ContactInfo.PhoneNumber;
import Group.Company;
import Group.CompanyList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {
    ContactList contacts;
    CompanyList companies;
    @BeforeEach
    public void setUp(){
        contacts = ContactList.getInstance();
        companies = CompanyList.getInstance();
    }

    @Test
    public void testBasicContactName(){
        String title = "Mr";
        String firstName = "Andrew";
        String lastName = "Goddard";
        ContactInfo info = new ContactInfo();
        String reference = "2000000";
        Contact contact = Contact.create(title, firstName, lastName, info, reference);
        assertEquals("Mr Andrew Goddard", contact.getName());
    }

    @Test
    public void testContactInfo(){
        String title = "Mr";
        String firstName = "Andrew";
        String lastName = "Goddard";
        ContactInfo info = new ContactInfo();
        String reference = "2000000";
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        info.add(PhoneNumber.create("07881266969"));
        Contact contact = Contact.create(title, firstName, lastName, info, reference);
        assertTrue(contact.hasContactInfoItem(EmailAddress.create("andersgoddard@gmail.com")));
    }

    @Test
    public void testContactFullName(){
        String name = "Mrs India Goddard";
        ContactInfo info = new ContactInfo();
        String reference = "2000000";
        Contact contact = Contact.create(name, info, reference);
        assertEquals("Mrs India Goddard", contact.getName());
    }

    @Test
    public void testContactAddedToContactList(){
        String name = "Mrs India Goddard";
        ContactInfo info = new ContactInfo();
        String reference = "2000000";
        Contact contact = Contact.create(name, info, reference);
        assertEquals(1, contacts.size());
    }

    @Test
    public void testContactHasUniqueIdentifier(){
        String name = "Mrs India Goddard";
        ContactInfo info = new ContactInfo();
        String reference = "2000000";
        Contact contact = Contact.create(name, info, reference);
        assertNotNull(contact.getUniqueIdentifier());
    }

    @Test
    public void testSameUniqueIdentifierForSameContact(){
        String name1 = "Mrs India Goddard";
        String name2 = "Ms I Goddard";
        PhoneNumber phone = PhoneNumber.create("07746142639");
        EmailAddress email1 = EmailAddress.create("indiabettsgoddard@outlook.com");
        EmailAddress email2 = EmailAddress.create("igoddard@marshandparsons.co.uk");

        ContactInfo info1 = new ContactInfo();
        info1.add(phone);
        info1.add(email1);
        ContactInfo info2 = new ContactInfo();
        info2.add(phone);
        info2.add(email2);

        String reference1 = "2000000";
        String reference2 = "2000001";

        Contact contact1 = Contact.create(name1, info1, reference1);
        Contact contact2 = Contact.create(name2, info2, reference2);
        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }

    @Test
    public void testContactShouldntMergeOnCompanySharedPhoneNumberOnContactCreation(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("02078932000"));
        Company company = Company.create("Example Company");
        company.setSharedContactInfo("02078932000");
        Contact contact1 = Contact.create("Mr Andrew Goddard", info, "2000");
        Contact contact2 = Contact.create("Mrs India Goddard", info, "2001");
        assertNotEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }

    @Test
    public void testContactShouldMergeOnNameAndCompanySharedContactInfo(){
        // Contacts shouldn't merge on name alone - there are many Mr John Smiths.
        // Contacts will merge on email address and phone numbers that aren't identified as shared phone numbers
        // However, the same name and a piece of contact info the same, even if a piece of shared contact info, should merge
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("02078932000"));
        Company company = Company.create("Example Company");
        company.setSharedContactInfo("02078932000");
        Contact contact1 = Contact.create("Mr Andrew Goddard", info, "000");
        Contact contact2 = Contact.create("Mr Andrew Goddard", info, "001");
        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }


    @Test
    public void testMergedContactShouldSplitOnCompanySharedPhoneNumberSet(){
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("02078932000"));
        Contact contact1 = Contact.create("Mr Andrew Goddard", info, "2000");
        Contact contact2 = Contact.create("Mrs India Goddard", info, "2001");
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

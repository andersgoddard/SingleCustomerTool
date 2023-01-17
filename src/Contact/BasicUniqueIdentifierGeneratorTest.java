package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.PhoneNumber;
import ContactInfo.EmailAddress;
import DatabaseFields.DatabaseFields;
import DatabaseFields.SimpleDatabaseFields;
import Group.CompanyList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BasicUniqueIdentifierGeneratorTest {
    ContactList contacts;
    CompanyList companies;
    DatabaseFields fields;
    UniqueIdentifierGenerator generator;

    @BeforeEach
    public void setUp(){
        contacts = ContactList.getInstance();
        companies = CompanyList.getInstance();
        fields = new SimpleDatabaseFields("Mr Andrew Goddard");
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        fields.setContactInfo(info);
        generator = new BasicUniqueIdentifierGenerator();
    }

    @Test
    public void testContactHasUniqueIdentifier(){
        Contact contact = Contact.create(fields);
        String uniqueIdentifier = generator.getUniqueIdentifierFor(contact);
        assertNotNull(uniqueIdentifier);
    }

    @Test
    public void testSameUniqueIdentifierForSameContact(){
        Contact contact1 = Contact.create(fields);
        DatabaseFields fields2 = new SimpleDatabaseFields("Mr A Goddard");
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andrewnmngoddard@outlook.com"));
        fields2.setContactInfo(info);
        Contact contact2 = Contact.create(fields2);
        String uniqueIdentifier1 = generator.getUniqueIdentifierFor(contact1);
        String uniqueIdentifier2 = generator.getUniqueIdentifierFor(contact2);
        assertEquals(uniqueIdentifier1, uniqueIdentifier2);
    }
}

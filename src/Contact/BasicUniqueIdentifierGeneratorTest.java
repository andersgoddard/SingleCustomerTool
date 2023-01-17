package Contact;

import Associaters.CompanyAssociater;
import ContactInfo.ContactInfo;
import ContactInfo.PhoneNumber;
import ContactInfo.EmailAddress;
import DatabaseFields.DatabaseFields;
import DatabaseFields.SimpleDatabaseFields;
import Group.CompanyList;
import Utilities.ContactFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BasicUniqueIdentifierGeneratorTest {
    ContactList contacts;
    CompanyList companies;
    DatabaseFields fields;
    UniqueIdentifierGenerator generator;
    CompanyAssociater associater;
    ContactFactory factory;


    @BeforeEach
    public void setUp(){
        contacts = ContactList.getInstance();
        companies = CompanyList.getInstance();
        associater = CompanyAssociater.create();
        factory = new ContactFactory();
        fields = new SimpleDatabaseFields("Mr Andrew Goddard");
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        fields.setContactInfo(info);
        generator = new BasicUniqueIdentifierGenerator();
    }

    @Test
    public void testContactHasUniqueIdentifier(){
        Contact contact = factory.create(fields, generator, associater, contacts);
        String uniqueIdentifier = generator.getUniqueIdentifierFor(contact);
        assertNotNull(uniqueIdentifier);
    }

    @Test
    public void testSameUniqueIdentifierForSameContact(){
        Contact contact1 = factory.create(fields, generator, associater, contacts);
        DatabaseFields fields2 = new SimpleDatabaseFields("Mr A Goddard");
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        info.add(EmailAddress.create("andrewnmngoddard@outlook.com"));
        fields2.setContactInfo(info);
        Contact contact2 = factory.create(fields2, generator, associater, contacts);
        String uniqueIdentifier1 = generator.getUniqueIdentifierFor(contact1);
        String uniqueIdentifier2 = generator.getUniqueIdentifierFor(contact2);
        assertEquals(uniqueIdentifier1, uniqueIdentifier2);
    }
}

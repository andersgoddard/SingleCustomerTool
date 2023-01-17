package Contact;

import Associaters.CompanyAssociater;
import ContactInfo.ContactInfo;
import ContactInfo.PhoneNumber;
import ContactInfo.EmailAddress;
import DatabaseFields.DatabaseFields;
import DatabaseFields.SimpleDatabaseFields;
import Utilities.ContactFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactMergerTest {
    @Test
    public void testMergeContacts(){
        ContactList contacts = ContactList.getInstance();
        CompanyAssociater associater = CompanyAssociater.create();
        UniqueIdentifierGenerator generator = new BasicUniqueIdentifierGenerator();
        ContactFactory factory = new ContactFactory();

        DatabaseFields fields1 = new SimpleDatabaseFields("Mr Andrew Goddard");
        DatabaseFields fields2 = new SimpleDatabaseFields("Mr A Goddard");

        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        fields1.setContactInfo(info);
        Contact andrew1 = factory.create(fields1, generator, associater, contacts);

        ContactInfo info2 = new ContactInfo();
        info2.add(EmailAddress.create("andersgoddard@gmail.com"));
        fields2.setContactInfo(info2);
        Contact andrew2 = factory.create(fields2, generator, associater, contacts);

        assertNotEquals(andrew1.getUniqueIdentifier(), andrew2.getUniqueIdentifier());
        ContactMerger.merge(andrew1, andrew2);
        assertEquals(andrew1.getUniqueIdentifier(), andrew2.getUniqueIdentifier());
        assertEquals(1, andrew1.getChildContacts().size());
        assertTrue(andrew1.getChildContacts().contains(andrew2));

        contacts.clear();
    }
}

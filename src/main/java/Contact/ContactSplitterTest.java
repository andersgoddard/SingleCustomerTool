package Contact;

import Associaters.CompanyAssociater;
import ContactInfo.ContactInfo;
import ContactInfo.PhoneNumber;
import ContactInfo.EmailAddress;
import DatabaseFields.DatabaseFields;
import DatabaseFields.SimpleDatabaseFields;
import Utilities.ContactFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ContactSplitterTest {
    @Test
    public void testBreakUpContact(){
        ContactList contacts = ContactList.getInstance();
        CompanyAssociater associater = CompanyAssociater.create();
        UniqueIdentifierGenerator generator = new BasicUniqueIdentifierGenerator();
        Injector injector = Guice.createInjector(new ContactFactoryModule());
        ContactFactory factory = injector.getInstance(ContactFactory.class);

        ContactInfo info = new ContactInfo();
        DatabaseFields andrewFields = new SimpleDatabaseFields("Mr Andrew Goddard");
        DatabaseFields indiaFields = new SimpleDatabaseFields("Mrs India Goddard");

        info.add(PhoneNumber.create("07881266969"));
        info.add(PhoneNumber.create("07746142639"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        andrewFields.setContactInfo(info);
        Contact andrew = factory.create(andrewFields);

        ContactInfo info2 = new ContactInfo();
        info2.add(EmailAddress.create("indiabettsgoddard@outlook.com"));
        info2.add(PhoneNumber.create("07746142639"));
        indiaFields.setContactInfo(info2);
        Contact india = factory.create(indiaFields);

        assertEquals(andrew.getUniqueIdentifier(), india.getUniqueIdentifier());
        assertEquals(1, andrew.getChildContacts().size());
        ContactSplitter.split(india);
        andrew.removeFromChildContacts(new ArrayList<>(List.of(india)));
        assertNotEquals(andrew.getUniqueIdentifier(), india.getUniqueIdentifier());
        assertEquals(0, andrew.getChildContacts().size());

        contacts.clear();
    }
}

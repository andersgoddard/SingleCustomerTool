package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoImpl;
import ContactInfo.PhoneNumber;
import ContactInfo.EmailAddress;
import DatabaseFields.DatabaseFields;
import DatabaseFields.DatabaseFieldsImpl;
import Directory.ContactDirectoryImpl;
import Utilities.ContactImplFactory;
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
        ContactDirectoryImpl contacts = ContactDirectoryImpl.getInstance();
        Injector injector = Guice.createInjector(new ContactFactoryModule());
        ContactImplFactory factory = injector.getInstance(ContactImplFactory.class);

        ContactInfo info = new ContactInfoImpl();
        info.add(PhoneNumber.create("07881266969"));
        info.add(PhoneNumber.create("07746142639"));
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        DatabaseFields andrewFields = new DatabaseFieldsImpl("Mr Andrew Goddard", info, null);
        Contact andrew = factory.create(andrewFields);

        ContactInfo info2 = new ContactInfoImpl();
        info2.add(EmailAddress.create("indiabettsgoddard@outlook.com"));
        info2.add(PhoneNumber.create("07746142639"));
        DatabaseFields indiaFields = new DatabaseFieldsImpl("Mrs India Goddard", info2, null);
        Contact india = factory.create(indiaFields);

        assertEquals(andrew.getUniqueIdentifier(), india.getUniqueIdentifier());
        assertEquals(1, andrew.getChildContacts().size());
        Splitter splitter = new ContactSplitter();
        splitter.split(india);
        andrew.removeFromChildContacts(new ArrayList<>(List.of(india)));
        assertNotEquals(andrew.getUniqueIdentifier(), india.getUniqueIdentifier());
        assertEquals(0, andrew.getChildContacts().size());

        contacts.clear();
    }
}

package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;
import DatabaseFields.DatabaseFields;
import Directory.ContactDirectory;
import Directory.CompanyDirectory;
import Directory.CompanyDirectoryImpl;
import Directory.ContactDirectoryImpl;
import Stubs.ContactInfoItemStub;
import Stubs.ContactInfoStub;
import Stubs.DatabaseFieldsStub;
import Utilities.ContactImplFactory;
import Utilities.ContactImplFactoryModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BasicUniqueIdentifierGeneratorTest {
    ContactDirectory contacts;
    CompanyDirectory companies;
    UniqueIdentifierGenerator generator = new BasicUniqueIdentifierGenerator();
    ContactImplFactory factory;


    @BeforeEach
    public void setUp(){
        contacts = ContactDirectoryImpl.getInstance();
        companies = CompanyDirectoryImpl.getInstance();
        Injector injector = Guice.createInjector(new ContactImplFactoryModule(companies));
        factory = injector.getInstance(ContactImplFactory.class);
    }

    @Test
    public void contactHasUniqueIdentifier(){
        Contact contact = factory.create(new DatabaseFieldsStub(){
            @Override
            public String getName() {
                return "Mr Andrew Goddard";
            }
        });

        String uniqueIdentifier = generator.getUniqueIdentifierFor(contact, contacts);

        assertNotNull(uniqueIdentifier);
    }

    @Test
    public void sameUniqueIdentifierForSameContact(){
        List<ContactInfoItem> contactInfo = getContactInfoWith("07881266969");
        DatabaseFields fields = getDatabaseFieldsWith("Mr Andrew Goddard", contactInfo);
        Contact contact1 = factory.create(fields);
        Contact contact2 = factory.create(fields);

        String uniqueIdentifier1 = generator.getUniqueIdentifierFor(contact1, contacts);
        String uniqueIdentifier2 = generator.getUniqueIdentifierFor(contact2, contacts);

        assertNotNull(uniqueIdentifier1);
        assertNotNull(uniqueIdentifier2);
        assertEquals(uniqueIdentifier1, uniqueIdentifier2);
    }

    private List<ContactInfoItem> getContactInfoWith(String contactInfo){
        return List.of(new ContactInfoItemStub(){
            @Override
            public String get() {
                return contactInfo;
            }
        });
    }

    private DatabaseFields getDatabaseFieldsWith(String name, List<ContactInfoItem> items){
        ContactInfo info = new ContactInfoStub(){
            @Override
            public List<ContactInfoItem> getItems() {
                return items;
            }

            @Override
            public boolean contains(ContactInfoItem item) {
                return getItems().contains(item);
            }
        };

        DatabaseFields fields = new DatabaseFieldsStub(){
            @Override
            public ContactInfo getContactInfo(){
                return info;
            }

            @Override
            public String getName(){
                return name;
            }
        };

        return fields;
    }
}

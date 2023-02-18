package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;
import Utilities.ContactImplFactory;
import Utilities.ContactImplFactoryModule;
import Stubs.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContactImplTest {
    ContactImplFactory factory;
    Injector injector;
    ContactRegistrar registrar;

    @BeforeEach
    public void setUp(){
        registrar = new ContactRegistrarImpl(new ContactDirectoryStub());
        injector = Guice.createInjector(new ContactImplFactoryModule(new CompanyDirectoryStub()));
        factory = injector.getInstance(ContactImplFactory.class);
    }

    @Test
    public void createWithPrimaryKey(){
        Contact contact = factory.create(new DatabaseFieldsStub(){
            @Override
            public String getPrimaryKey() {
                return "2000000";
            }
        }, registrar);

        assertEquals("2000000", contact.getDatabaseFields().getPrimaryKey());
    }

    @Test
    public void createWithName(){
        Contact contact = factory.create(new DatabaseFieldsStub(){
            @Override
            public String getName() {
                return "Mr Andrew Goddard";
            }
        }, registrar);

        assertEquals("Mr Andrew Goddard", contact.getDatabaseFields().getName());
    }

    @Test
    public void contactHasUniqueIdentifier(){
        Contact contact = factory.create(new DatabaseFieldsStub(), registrar);
        assertNotNull(contact.getUniqueIdentifier());
    }

    @Test
    public void contactHasSpecifiedUniqueIdentifier(){
        Contact contact = factory.create(new DatabaseFieldsStub(), registrar);
        contact.setUniqueIdentifier("test");
        assertEquals("test", contact.getUniqueIdentifier());
    }

    @Test
    public void contactHasCompanyId(){
        Contact contact = factory.create(new DatabaseFieldsStub(), registrar);
        contact.setCompanyId("test");
        assertEquals("test", contact.getCompanyId());
    }

    @Test
    public void contactHasChildContacts(){
        Contact contact = factory.create(new DatabaseFieldsStub(), registrar);
        Contact contact2 = factory.create(new DatabaseFieldsStub(), registrar);
        assertEquals(0, contact.getChildContacts().size());

        contact.addToChildContacts(contact2);
        assertEquals(1, contact.getChildContacts().size());

        contact.removeFromChildContacts(List.of(contact2));
        assertEquals(0, contact.getChildContacts().size());
    }

    @Test
    public void contactHasDatabaseFields(){
        Contact contact = factory.create(new DatabaseFieldsStub(){
            @Override
            public String getName(){
                return "Test";
            }
        }, registrar);

        assertEquals("Test", contact.getDatabaseFields().getName());
    }

    @Test
    public void contactHasContactInfoItemInSharedContactInfo(){
        ContactInfoItem email = new ContactInfoItemStub(){
            @Override
            public String get() {
                return "andersgoddard@gmail.com";
            }
        };

        ContactInfo info = new ContactInfoStub(){
            @Override
            public List<ContactInfoItem> getItems(){
                return List.of(email);
            }
        };

        Contact contact = factory.create(new DatabaseFieldsStub(){
            @Override
            public ContactInfo getContactInfo(){
                return info;
            }
        }, registrar);

        assertTrue(contact.hasContactInfoItemIn(info));
    }
    
    @Test
    public void contactHasContactInfoItem(){
        ContactInfoItem email = new ContactInfoItemStub(){
            @Override
            public String get() {
                return "andersgoddard@gmail.com";
            }
        };

        Contact contact = factory.create(new DatabaseFieldsStub(){
            @Override
            public ContactInfo getContactInfo(){
                return new ContactInfoStub(){
                    List<ContactInfoItem> items = List.of(email);
                    @Override
                    public boolean contains(ContactInfoItem item){
                        return items.contains(item);
                    }
                };
            }
        }, registrar);

        assertTrue(contact.hasContactInfoItem(email));
    }
}

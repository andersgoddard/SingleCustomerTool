package DatabaseFields;

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;
import Stubs.ContactInfoItemStub;
import Stubs.ContactInfoStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseFieldsImplTest {
    DatabaseFields fields;

    @BeforeEach
    public void setUp(){
        ContactInfoItem phone = new ContactInfoItemStub(){
            @Override
            public String get(){
                return "07881266969";
            }
        };

        ContactInfo info = new ContactInfoStub(){
            @Override
            public List<ContactInfoItem> getItems(){
                return List.of(phone);
            }
        };

        fields = new DatabaseFieldsImpl("Mr Andrew Goddard", info, "000"){};
    }

    @Test
    public void testDatabaseFieldsFullName(){
        assertEquals("Mr Andrew Goddard", fields.getName());
    }

    @Test
    public void fullNameFromNameComponents(){
        DatabaseFields fields = new DatabaseFieldsImpl("Mr", "Andrew", "Goddard",
                                                          new ContactInfoStub(),
                                                null);
        assertEquals("Mr Andrew Goddard", fields.getName());
    }

    @Test
    public void getterForPrimaryKey(){
        assertEquals("000", fields.getPrimaryKey());
    }

    @Test
    public void testContactInfoDatabaseFields(){
        assertEquals(1, fields.getContactInfo().getItems().size());
    }
}

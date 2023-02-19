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
    ContactInfo info;

    @BeforeEach
    public void setUp(){
        ContactInfoItem phone = new ContactInfoItemStub(){
            @Override
            public String get(){
                return "07881266969";
            }
        };

        this.info = new ContactInfoStub(){
            @Override
            public List<ContactInfoItem> getItems(){
                return List.of(phone);
            }
        };
    }

    @Test
    public void createWithFullName(){
        DatabaseFields fields = new DatabaseFieldsImpl("Mr Andrew Goddard", info, "000");

        assertEquals("Mr Andrew Goddard", fields.getName());
    }

    @Test
    public void createWithNameComponents(){
        DatabaseFields fields = new DatabaseFieldsImpl("Mr", "Andrew", "Goddard",
                                                          info,
                                                null);

        assertEquals("Mr Andrew Goddard", fields.getName());
    }

    @Test
    public void getterForPrimaryKey(){
        DatabaseFields fields = new DatabaseFieldsImpl("Mr Andrew Goddard", info, "000");

        assertEquals("000", fields.getPrimaryKey());
    }

    @Test
    public void getContactInfo(){
        DatabaseFields fields = new DatabaseFieldsImpl("Mr Andrew Goddard", info, "000");

        assertEquals(1, fields.getContactInfo().getItems().size());
    }
}

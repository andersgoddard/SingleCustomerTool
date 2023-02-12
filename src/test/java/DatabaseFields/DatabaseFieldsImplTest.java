package DatabaseFields;

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoImpl;
import ContactInfo.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DatabaseFieldsImplTest {
    DatabaseFields fields;

    @BeforeEach
    public void setUp(){
        ContactInfo info = new ContactInfoImpl();
        info.add(PhoneNumber.create("07881266969"));
        String primaryKey = "000";
        fields = new DatabaseFieldsImpl("Mr Andrew Goddard", info, primaryKey){};
    }

    @Test
    public void testDatabaseFieldsFullName(){
        assertEquals("Mr Andrew Goddard", fields.getName());
    }

    @Test
    public void tetDatabaseFieldsNameComponents(){
        DatabaseFields fields = new DatabaseFieldsImpl("Mr", "Andrew", "Goddard",
                                                          new ContactInfoImpl(),
                                                null);
        assertEquals("Mr Andrew Goddard", fields.getName());
    }

    @Test
    public void testContactInfoDatabaseFields(){
        assertTrue(fields.getContactInfo().contains(PhoneNumber.create("07881266969")));
    }

    @Test
    public void testDatabaseFieldsPrimaryKey(){
        assertEquals("000", fields.getPrimaryKey());
    }
}

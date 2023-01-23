package DatabaseFields;

import ContactInfo.Info;
import ContactInfo.ContactInfo;
import ContactInfo.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SimpleDatabaseFieldsTest {
    DatabaseFields fields;

    @BeforeEach
    public void setUp(){
        Info info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        String primaryKey = "000";
        fields = new SimpleDatabaseFields("Mr Andrew Goddard", info, primaryKey){};
    }

    @Test
    public void testDatabaseFieldsFullName(){
        assertEquals("Mr Andrew Goddard", fields.getName());
    }

    @Test
    public void tetDatabaseFieldsNameComponents(){
        DatabaseFields fields = new SimpleDatabaseFields("Mr", "Andrew", "Goddard",
                                                          new ContactInfo(),
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

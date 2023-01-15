package DatabaseFields;

import ContactInfo.ContactInfo;
import ContactInfo.PhoneNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SimpleDatabaseFieldsTest {
    @Test
    public void testDatabaseFieldsFullName(){
        DatabaseFields fields = new SimpleDatabaseFields("Mr Andrew Goddard");
        assertEquals("Mr Andrew Goddard", fields.getName());
    }

    @Test
    public void tetDatabaseFieldsNameComponents(){
        DatabaseFields fields = new SimpleDatabaseFields("Mr", "Andrew", "Goddard");
        assertEquals("Mr Andrew Goddard", fields.getName());
    }

    @Test
    public void testContactInfoDatabaseFields(){
        DatabaseFields fields = new SimpleDatabaseFields("Mr Andrew Goddard");
        ContactInfo info = new ContactInfo();
        info.add(PhoneNumber.create("07881266969"));
        fields.setContactInfo(info);
        assertTrue(fields.getContactInfo().contains(PhoneNumber.create("07881266969")));
    }

    @Test
    public void testDatabaseFieldsPrimaryKey(){
        DatabaseFields fields = new SimpleDatabaseFields("Mr Andrew Goddard");
        fields.setPrimaryKey("000");
        assertEquals("000", fields.getPrimaryKey());
    }
}

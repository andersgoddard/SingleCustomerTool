import ContactInfo.ContactInfo;
import ContactInfo.EmailAddress;
import ContactInfo.PhoneNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {
    @Test
    public void testBasicContact(){
        String title = "Mr";
        String firstName = "Andrew";
        String lastName = "Goddard";
        ContactInfo info = new ContactInfo();
        Contact contact = Contact.create(title, firstName, lastName, info);
        assertEquals("Mr Andrew Goddard", contact.getName());
    }

    @Test
    public void testContactAndContactInfo(){
        String title = "Mr";
        String firstName = "Andrew";
        String lastName = "Goddard";
        ContactInfo info = new ContactInfo();
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        info.add(PhoneNumber.create("07881266969"));
        Contact contact = Contact.create(title, firstName, lastName, info);
        assertTrue(contact.hasContactInfoItem(EmailAddress.create("andersgoddard@gmail.com")));
    }

    @Test
    public void testContactFullName(){
        String name = "Mrs India Goddard";
        ContactInfo info = new ContactInfo();
        Contact contact = Contact.create(name, info);
        assertEquals("Mrs India Goddard", contact.getName());
    }

    @Test
    public void testContactAddedToContactList(){
        ContactList list = getEmptyContactList();
        String name = "Mrs India Goddard";
        ContactInfo info = new ContactInfo();
        Contact contact = Contact.create(name, info);
        assertEquals(1, list.size());
        list.clear();
    }

    private ContactList getEmptyContactList() {
        ContactList list = ContactList.getInstance();
        list.clear();
        return ContactList.getInstance();
    }

    @Test
    public void testContactHasUniqueIdentifier(){
        String name = "Mrs India Goddard";
        ContactInfo info = new ContactInfo();
        Contact contact = Contact.create(name, info);
        assertNotNull(contact.getUniqueIdentifier());
    }

    @Test
    public void testSameUniqueIdentifierForSameContact(){
        String name1 = "Mrs India Goddard";
        String name2 = "Ms I Goddard";
        String phone = "07746142639";
        String email1 = "indiabettsgoddard@outlook.com";
        String email2 = "igoddard@marshandparsons.co.uk";

        ContactInfo info1 = new ContactInfo();
        info1.add(PhoneNumber.create(phone));
        info1.add(EmailAddress.create(email1));
        ContactInfo info2 = new ContactInfo();
        info2.add(PhoneNumber.create(phone));
        info2.add(EmailAddress.create(email2));

        Contact contact1 = Contact.create(name1, info1);
        Contact contact2 = Contact.create(name2, info2);
        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }
}

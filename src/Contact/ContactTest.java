package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.EmailAddress;
import ContactInfo.PhoneNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {
    @Test
    public void testBasicContactName(){
        String title = "Mr";
        String firstName = "Andrew";
        String lastName = "Goddard";
        ContactInfo info = new ContactInfo();
        String reference = "2000000";
        Contact contact = Contact.create(title, firstName, lastName, info, reference);
        assertEquals("Mr Andrew Goddard", contact.getName());
    }

    @Test
    public void testContactInfo(){
        String title = "Mr";
        String firstName = "Andrew";
        String lastName = "Goddard";
        ContactInfo info = new ContactInfo();
        String reference = "2000000";
        info.add(EmailAddress.create("andersgoddard@gmail.com"));
        info.add(PhoneNumber.create("07881266969"));
        Contact contact = Contact.create(title, firstName, lastName, info, reference);
        assertTrue(contact.hasContactInfoItem(EmailAddress.create("andersgoddard@gmail.com")));
    }

    @Test
    public void testContactFullName(){
        String name = "Mrs India Goddard";
        ContactInfo info = new ContactInfo();
        String reference = "2000000";
        Contact contact = Contact.create(name, info, reference);
        assertEquals("Mrs India Goddard", contact.getName());
    }

    @Test
    public void testContactAddedToContactList(){
        ContactList list = getEmptyContactList();
        String name = "Mrs India Goddard";
        ContactInfo info = new ContactInfo();
        String reference = "2000000";
        Contact contact = Contact.create(name, info, reference);
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
        String reference = "2000000";
        Contact contact = Contact.create(name, info, reference);
        assertNotNull(contact.getUniqueIdentifier());
    }

    @Test
    public void testSameUniqueIdentifierForSameContact(){
        String name1 = "Mrs India Goddard";
        String name2 = "Ms I Goddard";
        PhoneNumber phone = PhoneNumber.create("07746142639");
        EmailAddress email1 = EmailAddress.create("indiabettsgoddard@outlook.com");
        EmailAddress email2 = EmailAddress.create("igoddard@marshandparsons.co.uk");

        ContactInfo info1 = new ContactInfo();
        info1.add(phone);
        info1.add(email1);
        ContactInfo info2 = new ContactInfo();
        info2.add(phone);
        info2.add(email2);

        String reference1 = "2000000";
        String reference2 = "2000001";

        Contact contact1 = Contact.create(name1, info1, reference1);
        Contact contact2 = Contact.create(name2, info2, reference2);
        assertEquals(contact1.getUniqueIdentifier(), contact2.getUniqueIdentifier());
    }
}

import java.util.ArrayList;
import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;
public class ContactList {
    private ArrayList<Contact> contacts;
    private static ContactList list = null;

    private ContactList() {
        contacts = new ArrayList<>();
    }

    public static ContactList getInstance() {
        if (list == null)
            list = new ContactList();

        return list;
    }

    public void clear() {
        list = null;
        contacts = null;
    }

    public int size() {
        return contacts.size();
    }

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public Contact contains(ContactInfo info) {
        for (Contact contact : contacts){
            for (ContactInfoItem item : info.getItems()){
                if (contact.hasContactInfoItem(item))
                    return contact;
            }
        }
        return null;
    }
}

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;

import java.util.UUID;

public class Contact {
    private final String fullName;
    private final ContactInfo info;
    private String uniqueIdentifier;

    private Contact(String name, ContactInfo info) {
        this.fullName = name;
        this.info = info;
        setUniqueIdentifier();
        ContactList.getInstance().add(this);
    }

    private void setUniqueIdentifier() {
        ContactList allContacts = ContactList.getInstance();
        Contact similarContact = allContacts.contains(info);
        if (similarContact == null) {
            this.uniqueIdentifier = UUID.randomUUID().toString();
        } else {
            this.uniqueIdentifier = similarContact.getUniqueIdentifier();
        }
    }

    public static Contact create(String name, ContactInfo info){
        return new Contact(name, info);
    }

    public static Contact create(String title, String firstName, String lastName, ContactInfo info){
        return new Contact(title + " " + firstName + " " + lastName, info);
    }

    public String getName() {
        return fullName;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public boolean hasContactInfoItem(ContactInfoItem item) {
        return info.contains(item);
    }
}

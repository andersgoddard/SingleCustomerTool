package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;

import java.util.UUID;

public class Contact {
    private final String fullName;
    private final ContactInfo info;
    private String uniqueIdentifier;
    private final String reference;

    private Contact(String name, ContactInfo info, String reference) {
        this.fullName = name;
        this.info = info;
        this.reference = reference;
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

    public static Contact create(String name, ContactInfo info, String reference){
        return new Contact(name, info, reference);
    }

    public static Contact create(String title, String firstName, String lastName, ContactInfo info, String reference){
        return new Contact(title + " " + firstName + " " + lastName, info, reference);
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

    public void setNewUniqueIdentifier() {
        this.uniqueIdentifier = UUID.randomUUID().toString();
    }

    public void setNewUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }
}

package Contact;

import java.util.UUID;

public class BasicUniqueIdentifierGenerator implements UniqueIdentifierGenerator {
    public String getUniqueIdentifierFor(Contact contact) {
        String uniqueIdentifier;
        ContactList allContacts = ContactList.getInstance();
        Contact similarContact = allContacts.contains(contact.getDatabaseFields().getName(), contact.getDatabaseFields().getContactInfo());
        if (similarContact == null) {
            uniqueIdentifier = UUID.randomUUID().toString();
        } else {
            ContactMerger.merge(similarContact, contact);
            uniqueIdentifier = similarContact.getUniqueIdentifier();
        }
        return uniqueIdentifier;
    }
}

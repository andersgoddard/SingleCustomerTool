package Contact;

import com.google.inject.Inject;

import java.util.UUID;

public class BasicUniqueIdentifierGenerator implements UniqueIdentifierGenerator {
    @Inject
    public BasicUniqueIdentifierGenerator(){}
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

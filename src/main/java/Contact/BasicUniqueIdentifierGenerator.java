package Contact;

import Directory.ContactDirectoryImpl;
import com.google.inject.Inject;

import java.util.UUID;

public class BasicUniqueIdentifierGenerator implements UniqueIdentifierGenerator {
    @Inject
    public BasicUniqueIdentifierGenerator(){}
    public String getUniqueIdentifierFor(Contact contact) {
        String uniqueIdentifier;
        Contact similarContact = ContactDirectoryImpl.getInstance()
                                .contains(contact.getDatabaseFields().getName(),
                                        contact.getDatabaseFields().getContactInfo());
        if (similarContact == null) {
            uniqueIdentifier = UUID.randomUUID().toString();
        } else {
            Merger merger = new ContactMerger();
            merger.merge(similarContact, contact);
            uniqueIdentifier = similarContact.getUniqueIdentifier();
        }
        return uniqueIdentifier;
    }
}

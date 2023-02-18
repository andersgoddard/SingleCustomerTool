package Contact;

import Directory.ContactDirectory;
import com.google.inject.Inject;

import java.util.UUID;

public class BasicUniqueIdentifierGenerator implements UniqueIdentifierGenerator {
    @Inject
    public BasicUniqueIdentifierGenerator(){}

    public String getUniqueIdentifierFor(Contact contact, ContactDirectory directory) {
        String uniqueIdentifier;
        Contact similarContact = directory.contains(contact.getDatabaseFields().getName(),
                                        contact.getDatabaseFields().getContactInfo());


        if (similarContact == null) {
            uniqueIdentifier = UUID.randomUUID().toString();
        } else {
            Merger merger = new ContactMerger(); // Concrete Dependency
            merger.merge(similarContact, contact);
            uniqueIdentifier = similarContact.getUniqueIdentifier();
        }

        return uniqueIdentifier;
    }
}

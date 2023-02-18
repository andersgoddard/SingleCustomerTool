package Contact;

import Directory.ContactDirectory;

public interface UniqueIdentifierGenerator {
    String getUniqueIdentifierFor(Contact contact, ContactDirectory directory);
}

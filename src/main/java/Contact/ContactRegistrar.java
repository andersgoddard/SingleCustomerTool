package Contact;

import Directory.ContactDirectory;

public interface ContactRegistrar {
    void register(Contact contact);
    ContactDirectory getContactDirectory();
}

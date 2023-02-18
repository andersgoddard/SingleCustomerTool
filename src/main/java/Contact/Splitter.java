package Contact;

import Directory.ContactDirectory;

public interface Splitter {
    void separateIncorrectlyMergedContacts(ContactDirectory contacts);
    void split(Contact main, Contact incorrectlyMergedContact);
}

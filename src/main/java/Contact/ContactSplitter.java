package Contact;

import Directory.ContactDirectory;

import java.util.ArrayList;
import java.util.List;

public class ContactSplitter implements Splitter {
    public void separateIncorrectlyMergedContacts(ContactDirectory contacts) {
        for (Contact contact : contacts.get()) {
            List<Contact> children = contact.getChildContacts();
            List<Contact> removedChildren = new ArrayList<>();
            if (children != null) {
                for (Contact child : children) {
                    child.setUniqueIdentifier();
                    removedChildren.add(child);
                }
                contact.removeFromChildContacts(removedChildren);
            }
        }
    }

    public void split(Contact main, Contact incorrectlyMergedContact) {
        incorrectlyMergedContact.setUniqueIdentifier();
        main.removeFromChildContacts(List.of(incorrectlyMergedContact));
    }
}

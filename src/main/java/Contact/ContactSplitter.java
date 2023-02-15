package Contact;

import Directory.ContactDirectory;

import java.util.ArrayList;
import java.util.List;

/* Represents a tool to split two Contacts that have been merged into one but do not represent the same person in the real world */
public class ContactSplitter implements Splitter {
    public void separateIncorrectlyMergedContacts(ContactDirectory contacts) {
        for (Contact contact : contacts.get()) {
            List<Contact> children = contact.getChildContacts();
            List<Contact> removedChildren = new ArrayList<>();
            if (children != null) {
                for (Contact child : children) {
                    split(child);
                    removedChildren.add(child);
                }
                contact.removeFromChildContacts(removedChildren);
            }
        }
    }

    public void split(Contact incorrectlyMergedContact) {
        incorrectlyMergedContact.setUniqueIdentifier();
    }
}

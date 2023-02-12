package Contact;

import Associaters.Associatable;
import Directory.Directory;

import java.util.ArrayList;
import java.util.List;

/* Represents a tool to split two Contacts that have been merged into one but do not represent the same person in the real world */
public class ContactSplitter {
    public void separateIncorrectlyMergedContacts(Directory contacts) {
        for (Associatable associatable : contacts.get()) {
            ContactImpl contact = (ContactImpl) associatable;
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

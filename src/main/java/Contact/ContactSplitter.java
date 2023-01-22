package Contact;

/* Represents a tool to split two Contacts that have been merged into one but do not represent the same person in the real world */
public class ContactSplitter {

    /*  The candidate Contact's UUID is reset to a random UUID. The main Contact is not used. */
    public void split(Contact incorrectlyMergedContact) {
        incorrectlyMergedContact.setNewUniqueIdentifier();
    }
}

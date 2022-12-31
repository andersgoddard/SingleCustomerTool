package Contact;

/* Represents a tool to merge two Contacts in a database that represent the same person in the real world */
public class ContactMerger {

/*  To 'merge' the candidate Contact with the main Contact, the candidate Contact's UUID is set to the main Contact's UUID */
    public static void merge(Contact candidate, Contact main) {
        candidate.setNewUniqueIdentifier(main.getUniqueIdentifier());
    }
}

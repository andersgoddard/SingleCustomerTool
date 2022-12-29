package Contact;

public class ContactMerger {

    public static void merge(Contact main, Contact candidate) {
        candidate.setNewUniqueIdentifier(main.getUniqueIdentifier());
    }
}

package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;
import ContactInfo.EmailAddress;
import Associaters.Associatable;
import Associaters.CompanyAssociater;
import DatabaseFields.DatabaseFields;

import java.util.ArrayList;
import java.util.UUID;

/* Represents a single contact, for example a person in a database. Largely a data-carrying class. */

public class Contact implements Associatable {
    private String uniqueIdentifier;
    private ArrayList<Contact> childContacts;
    private String companyId;
    private final DatabaseFields fields;

    public static Contact create(DatabaseFields fields) {
        return new Contact(fields);
    }

    private Contact(DatabaseFields fields) {
        this.fields = fields;
        setUniqueIdentifier();
        CompanyAssociater.create().associate(this);
        ContactList.getInstance().add(this);
    }

    private void setUniqueIdentifier() {
        /*    Checks the global list of Contacts (the singleton class ContactList) for Contacts containing any of the contact information
         *    contained in this Contact. If one is found, the UUID for that Contact is set as the UUID for this one, otherwise a new random
         *    UUID is set.
         */
        ContactList allContacts = ContactList.getInstance();
        Contact similarContact = allContacts.contains(fields.getName(), fields.getContactInfo());
        if (similarContact == null) {
            this.uniqueIdentifier = UUID.randomUUID().toString();
        } else {
            ContactMerger.merge(similarContact, this);
        }
    }

    // Getter Methods
    public String getName() {
        return fields.getName();
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public String getCompanyId() {
        return companyId;
    }

    public ArrayList<Contact> getChildContacts() {
        return childContacts;
    }

    public ArrayList<String> getEmailDomains(){
        ArrayList<String> emailDomains = new ArrayList<>();
        for (ContactInfoItem item : fields.getContactInfo().getItems()) {
            if (item.getClass() == EmailAddress.class) {
                emailDomains.add(((EmailAddress)item).getEmailDomain());
            }
        }
        return emailDomains;
    }

    // Setter Methods
    public void setNewUniqueIdentifier() {
        /*  Sets a new random UUID for the Contact. Used by the ContactSplitter class.*/
        this.uniqueIdentifier = UUID.randomUUID().toString();
    }

    public void setNewUniqueIdentifier(String uniqueIdentifier) {
        /*  Sets the String uniqueIdentifier as the UUID for the Contact. Used by the ContactMerger class. */
        this.uniqueIdentifier = uniqueIdentifier;
    }


    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public boolean hasContactInfoItem(ContactInfoItem item) {
        return fields.getContactInfo().contains(item);
    }

//    public boolean hasContactInfoItem(ContactInfoItem item){
//        return fields.getContactInfo().contains(item);
//    }

    public boolean hasEmailDomain(String emailDomain) {
        for (String domain : getEmailDomains()){
            if (domain.equals(emailDomain))
                return true;
        }
        return false;
    }

    public boolean hasContactInfoItemIn(ContactInfo sharedContactInfo) {
        for (ContactInfoItem item : sharedContactInfo.getItems()){
            for (ContactInfoItem existingItem : fields.getContactInfo().getItems()){
                if (item.get().equals(existingItem.get()))
                    return true;
            }
        }
        return false;
    }

    public void addToChildContacts(Contact contact) {
        if (childContacts == null)
            childContacts = new ArrayList<>();
        childContacts.add(contact);
    }

    public void removeFromChildContacts(ArrayList<Contact> children) {
        childContacts.removeAll(children);
    }

    public DatabaseFields getDatabaseFields() {
        return fields;
    }
}

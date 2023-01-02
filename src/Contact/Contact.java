package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;
import ContactInfo.EmailAddress;
import Associaters.Associatable;
import Associaters.CompanyAssociater;

import java.util.ArrayList;
import java.util.UUID;

/* Represents a single contact, for example a person in a database. Largely a data-carrying class. */

public class Contact implements Associatable {

    private final String fullName;
    private final ContactInfo info;
    private String uniqueIdentifier;
    private ArrayList<Contact> childContacts;
    private final String reference;
    private String companyId;

    private Contact(String name, ContactInfo info, String reference) {
        this.fullName = name;
        this.info = info;
        this.reference = reference;
        setUniqueIdentifier();
        CompanyAssociater.create().associate(this);
        ContactList.getInstance().add(this);
    }

    public static Contact create(String name, ContactInfo info, String reference){
        return new Contact(name, info, reference);
    }

    public static Contact create(String title, String firstName, String lastName, ContactInfo info, String reference){
        return new Contact(title + " " + firstName + " " + lastName, info, reference);
    }

    // Getter Methods
    public String getName() {
        return fullName;
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
        for (ContactInfoItem item : info.getItems()) {
            if (item.getClass() == EmailAddress.class) {
                emailDomains.add(((EmailAddress)item).getEmailDomain());
            }
        }
        return emailDomains;
    }

    // Setter Methods
    private void setUniqueIdentifier() {
        /*    Checks the global list of Contacts (the singleton class ContactList) for Contacts containing any of the contact information
         *    contained in this Contact. If one is found, the UUID for that Contact is set as the UUID for this one, otherwise a new random
         *    UUID is set.
         */
        ContactList allContacts = ContactList.getInstance();
        Contact similarContact = allContacts.contains(fullName, info);
        if (similarContact == null) {
            this.uniqueIdentifier = UUID.randomUUID().toString();
        } else {
            ContactMerger.merge(similarContact, this);
        }
    }

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
        return info.contains(item);
    }

    public boolean hasEmailDomain(String emailDomain) {
        for (String domain : getEmailDomains()){
            if (domain.equals(emailDomain))
                return true;
        }
        return false;
    }

    public boolean hasContactInfoItemIn(ContactInfo sharedContactInfo) {
        for (ContactInfoItem item : sharedContactInfo.getItems()){
            for (ContactInfoItem existingItem : info.getItems()){
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

    public String getReference() {
        return reference;
    }
}

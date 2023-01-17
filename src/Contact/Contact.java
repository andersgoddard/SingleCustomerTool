package Contact;

import ContactInfo.Info;
import ContactInfo.ContactInfoItem;
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
        setUniqueIdentifier(new BasicUniqueIdentifierGenerator());
        CompanyAssociater.create().associate(this);
        ContactList.getInstance().add(this);
    }

    private void setUniqueIdentifier(UniqueIdentifierGenerator generator){
        this.uniqueIdentifier = generator.getUniqueIdentifierFor(this);
    }

    public void setNewUniqueIdentifier() {
        /*  Sets a new random UUID for the Contact. Used by the ContactSplitter class.*/
        this.uniqueIdentifier = UUID.randomUUID().toString();
    }

    public void setNewUniqueIdentifier(String uniqueIdentifier) {
        /*  Sets the String uniqueIdentifier as the UUID for the Contact. Used by the ContactMerger class. */
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public boolean hasContactInfoItemIn(Info sharedContactInfo) {
        for (ContactInfoItem item : sharedContactInfo.getItems()){
            for (ContactInfoItem existingItem : fields.getContactInfo().getItems()){
                if (item.get().equals(existingItem.get()))
                    return true;
            }
        }
        return false;
    }

    public boolean hasContactInfoItem(ContactInfoItem item) {
        return fields.getContactInfo().contains(item);
    }

    public boolean hasEmailDomain(String emailDomain) {
        for (String domain : getEmailDomains()){
            if (domain.equals(emailDomain))
                return true;
        }
        return false;
    }

    private ArrayList<String> getEmailDomains(){
        ArrayList<String> emailDomains = new ArrayList<>();
        for (ContactInfoItem item : fields.getContactInfo().getItems()) {
            if (item.isEmail()) {
                emailDomains.add(item.getEmailDomain());
            }
        }
        return emailDomains;
    }

    public void addToChildContacts(Contact contact) {
        if (childContacts == null)
            childContacts = new ArrayList<>();
        childContacts.add(contact);
    }

    public void removeFromChildContacts(ArrayList<Contact> children) {
        childContacts.removeAll(children);
    }

    public String getName() {
        return fields.getName();
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public ArrayList<Contact> getChildContacts() {
        return childContacts;
    }

    public DatabaseFields getDatabaseFields() {
        return fields;
    }
}

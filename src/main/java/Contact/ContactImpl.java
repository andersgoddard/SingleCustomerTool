package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;
import DatabaseFields.DatabaseFields;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

/* Represents a single contact, for example a person in a database. Largely a data-carrying class. */

public class ContactImpl implements Contact {
    private String uniqueIdentifier;
    private final List<Contact> childContacts;
    private String companyId;
    private final DatabaseFields fields;

    public static ContactImpl create(DatabaseFields fields) {
        return new ContactImpl(fields);
    }

    private ContactImpl(DatabaseFields fields) {
        childContacts = new ArrayList<>();
        this.fields = fields;
    }

    public void setUniqueIdentifier() {
        /*  Sets a new random UUID for the Contact. Used by the ContactSplitter class.*/
        this.uniqueIdentifier = UUID.randomUUID().toString();
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        /*  Sets the String uniqueIdentifier as the UUID for the Contact. Used by the ContactMerger class. */
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
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

    private List<String> getEmailDomains(){
        List<String> emailDomains = new ArrayList<>();
        for (ContactInfoItem item : fields.getContactInfo().getItems()) {
            if (item.isEmail()) {
                emailDomains.add(item.getEmailDomain());
            }
        }
        return emailDomains;
    }

    public void addToChildContacts(Contact contact) {
        childContacts.add(contact);
    }

    public void removeFromChildContacts(List<Contact> children) {
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

    public List<Contact> getChildContacts() {
        return childContacts;
    }

    public DatabaseFields getDatabaseFields() {
        return fields;
    }
}

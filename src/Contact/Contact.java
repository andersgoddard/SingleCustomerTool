package Contact;

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;
import ContactInfo.EmailAddress;
import ContactInfo.PhoneNumber;
import Group.Company;
import Group.CompanyList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* Represents a single contact, for example a person in a database. Largely a data-carrying class. */

public class Contact {

    private final String fullName;
    private final ContactInfo info;
    private String uniqueIdentifier;
    private final String reference;
    private String companyId;

    private Contact(String name, ContactInfo info, String reference) {
        this.fullName = name;
        this.info = info;
        this.reference = reference;
        setUniqueIdentifier();
        setCompanyId(companyIdForEmailDomain());
        ContactList.getInstance().add(this);
    }

    /*    Checks the global list of Contacts (the singleton class ContactList) for Contacts containing any of the contact information
 *    contained in this Contact. If one is found, the UUID for that Contact is set as the UUID for this one, otherwise a new random
 *    UUID is set.
 */
    private void setUniqueIdentifier() {
        ContactList allContacts = ContactList.getInstance();
        Contact similarContact = allContacts.contains(info);
        if (similarContact == null) {
            this.uniqueIdentifier = UUID.randomUUID().toString();
        } else {
            this.uniqueIdentifier = similarContact.getUniqueIdentifier();
        }
    }

    private String companyIdForEmailDomain() {
        CompanyList allCompanies = CompanyList.getInstance();
        for (Company company : allCompanies.getCompanies()){
            if (this.hasEmailDomain(company.getEmailDomain()))
                return company.getUniqueIdentifier();
        }
        return null;
    }

    public static Contact create(String name, ContactInfo info, String reference){
        return new Contact(name, info, reference);
    }

    public static Contact create(String title, String firstName, String lastName, ContactInfo info, String reference){
        return new Contact(title + " " + firstName + " " + lastName, info, reference);
    }

    public String getName() {
        return fullName;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public boolean hasContactInfoItem(ContactInfoItem item) {
        return info.contains(item);
    }

/*  Sets a new random UUID for the Contact. Used by the ContactSplitter class.*/
    public void setNewUniqueIdentifier() {
        this.uniqueIdentifier = UUID.randomUUID().toString();
    }

/*  Sets the String uniqueIdentifier as the UUID for the Contact. Used by the ContactMerger class.*/
    public void setNewUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public boolean hasEmailDomain(String emailDomain) {
        for (String domain : getEmailDomains()){
            if (domain.equals(emailDomain))
                return true;
        }
        return false;
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

    public boolean hasPhoneNumberIn(List<PhoneNumber> sharedPhoneNumbers) {
        for (PhoneNumber number : sharedPhoneNumbers){
            for (ContactInfoItem item : info.getItems()){
                if (number.get().equals(item.get()))
                    return true;
            }
        }
        return false;
    }
}

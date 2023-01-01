package Group;

import Contact.Contact;

public class CompanyAssociater implements Associater {

    public static void associate(Contact contact){
        contact.setCompanyId(getCompanyIdFromEmailDomain(contact));
        contact.setCompanyId(getCompanyIdFromContactInfo(contact));
    }

    private static String getCompanyIdFromEmailDomain(Contact contact) { // CompanyAssociater?
        CompanyList allCompanies = CompanyList.getInstance();
        for (Company company : allCompanies.getCompanies()){
            if (contact.hasEmailDomain(company.getEmailDomain()))
                return company.getUniqueIdentifier();
        }
        return contact.getCompanyId();
    }

    private static String getCompanyIdFromContactInfo(Contact contact) {  // CompanyAssociater?
        CompanyList allCompanies = CompanyList.getInstance();
        for (Company company : allCompanies.getCompanies()){
            if (contact.hasContactInfoItemIn(company.getSharedContactInfo()))
                return company.getUniqueIdentifier();
        }
        return contact.getCompanyId();
    }

}

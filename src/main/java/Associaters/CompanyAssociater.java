package Associaters;

import Contact.Contact;
import Group.Company;
import Group.CompanyList;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class CompanyAssociater implements Associater {
    @Provides
    @Singleton
    public static CompanyAssociater create(){
        return new CompanyAssociater();
    }

    public void associate(Associatable associatable){
        Contact contact = (Contact)associatable;
        contact.setCompanyId(getCompanyIdFromEmailDomain(contact));
        contact.setCompanyId(getCompanyIdFromContactInfo(contact));
    }

    private static String getCompanyIdFromEmailDomain(Contact contact) {
        CompanyList allCompanies = CompanyList.getInstance();
        for (Company company : allCompanies.getCompanies()){
            if (contact.hasEmailDomain(company.getEmailDomain()))
                return company.getUniqueIdentifier();
        }
        return contact.getCompanyId();
    }

    private static String getCompanyIdFromContactInfo(Contact contact) {
        CompanyList allCompanies = CompanyList.getInstance();
        for (Company company : allCompanies.getCompanies()){
            if (contact.hasContactInfoItemIn(company.getSharedContactInfo()))
                return company.getUniqueIdentifier();
        }
        return contact.getCompanyId();
    }
}

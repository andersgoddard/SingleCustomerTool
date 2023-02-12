package Associaters;

import Contact.ContactImpl;
import Group.Company;
import Directory.Directory;
import Directory.CompanyDirectory;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class CompanyAssociater implements Associater {
    @Provides
    @Singleton
    public static CompanyAssociater create(){
        return new CompanyAssociater();
    }

    public void associate(Associatable associatable){
        ContactImpl contact = (ContactImpl)associatable;
        contact.setCompanyId(getCompanyIdFromEmailDomain(contact));
        contact.setCompanyId(getCompanyIdFromContactInfo(contact));
    }

    private static String getCompanyIdFromEmailDomain(ContactImpl contact) {
        Directory allCompanies = CompanyDirectory.getInstance();
        for (Associatable associatable : allCompanies.get()){
            Company company = (Company)associatable;
            if (contact.hasEmailDomain(company.getEmailDomain()))
                return company.getUniqueIdentifier();
        }
        return contact.getCompanyId();
    }

    private static String getCompanyIdFromContactInfo(ContactImpl contact) {
        Directory allCompanies = CompanyDirectory.getInstance();
        for (Associatable associatable : allCompanies.get()){
            Company company = (Company)associatable;
            if (contact.hasContactInfoItemIn(company.getSharedContactInfo()))
                return company.getUniqueIdentifier();
        }
        return contact.getCompanyId();
    }
}

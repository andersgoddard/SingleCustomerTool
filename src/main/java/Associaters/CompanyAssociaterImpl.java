package Associaters;

import Contact.Contact;
import Group.Company;
import Directory.CompanyDirectory;
import Directory.CompanyDirectoryImpl; // Concrete Dependency
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class CompanyAssociaterImpl implements CompanyAssociater {
    @Provides
    @Singleton
    public static CompanyAssociaterImpl create(){
        return new CompanyAssociaterImpl();
    }

    public void associate(Contact contact){
        contact.setCompanyId(getCompanyIdFromEmailDomain(contact));
        contact.setCompanyId(getCompanyIdFromContactInfo(contact));
    }

    private String getCompanyIdFromEmailDomain(Contact contact) {
        CompanyDirectory allCompanies = CompanyDirectoryImpl.getInstance();
        for (Company company : allCompanies.get()){
            if (contact.hasEmailDomain(company.getEmailDomain()))
                return company.getUniqueIdentifier();
        }
        return contact.getCompanyId();
    }

    private String getCompanyIdFromContactInfo(Contact contact) {
        CompanyDirectory allCompanies = CompanyDirectoryImpl.getInstance();
        for (Company company : allCompanies.get()){
            if (contact.hasContactInfoItemIn(company.getSharedContactInfo()))
                return company.getUniqueIdentifier();
        }
        return contact.getCompanyId();
    }
}

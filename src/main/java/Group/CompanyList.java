package Group;

import ContactInfo.ContactInfoItem;

import java.util.ArrayList;

public class CompanyList {
    private static CompanyList list = null;
    private final ArrayList<Company> companies;
    private CompanyList() {
        companies = new ArrayList<>();
    }

    public static CompanyList getInstance() {
        if (list == null)
            list = new CompanyList();

        return list;
    }

    public void clear() {
        list = null;
        companies.clear();
    }

    public int size() {
        return companies.size();
    }

    public void add(Company company) {
        companies.add(company);
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public boolean doesNotContain(ContactInfoItem item) {
        for (Company company : list.getCompanies()){
            if (company.hasSharedContactInfoItem(item))
                return false;
        }
        return true;
    }
}

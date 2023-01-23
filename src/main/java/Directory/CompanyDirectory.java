package Directory;

import Associaters.Associatable;
import ContactInfo.ContactInfoItem;
import Group.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyDirectory implements Directory {
    private static CompanyDirectory list = null;
    private final List<Associatable> companies;
    private CompanyDirectory() {
        companies = new ArrayList<>();
    }

    public static CompanyDirectory getInstance() {
        if (list == null)
            list = new CompanyDirectory();

        return list;
    }

    public void clear() {
        list = null;
        companies.clear();
    }

    @Override
    public List<Associatable> get() {
        return companies;
    }

    @Override
    public void add(Associatable company) {
        companies.add(company);
    }

    public int size() {
        return companies.size();
    }

    public void add(Company company) {
        companies.add(company);
    }

    public boolean doesNotContain(ContactInfoItem item) {
        for (Associatable associatable : list.get()){
            Company company = (Company)associatable;
            if (company.hasSharedContactInfoItem(item))
                return false;
        }
        return true;
    }
}

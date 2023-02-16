package Directory;

import ContactInfo.ContactInfoItem;
import Group.Company;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

public class CompanyDirectoryImpl implements CompanyDirectory {
    private static CompanyDirectoryImpl list = null;
    private final List<Company> companies;
    CompanyDirectoryImpl() {
        companies = new ArrayList<>();
    }

    @Provides
    @Singleton
    public static CompanyDirectoryImpl getInstance() {
        if (list == null)
            list = new CompanyDirectoryImpl();

        return list;
    }

    @Override
    public void clear() {
        list = null;
        companies.clear();
    }

    @Override
    public List<Company> get() {
        return companies;
    }

    @Override
    public void add(Company company) {
        companies.add(company);
    }

    @Override
    public int size() {
        return companies.size();
    }

    public boolean doesNotContain(ContactInfoItem item) {
        for (Company company : list.get()){
            if (company.hasSharedContactInfoItem(item))
                return false;
        }
        return true;
    }
}

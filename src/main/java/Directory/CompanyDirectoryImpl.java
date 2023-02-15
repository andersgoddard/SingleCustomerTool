package Directory;

import ContactInfo.ContactInfoItem;
import Group.CompanyImpl;

import java.util.ArrayList;
import java.util.List;

public class CompanyDirectoryImpl implements CompanyDirectory {
    private static CompanyDirectoryImpl list = null;
    private final List<CompanyImpl> companies;
    private CompanyDirectoryImpl() {
        companies = new ArrayList<>();
    }

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
    public List<CompanyImpl> get() {
        return companies;
    }

    @Override
    public void add(CompanyImpl company) {
        companies.add(company);
    }

    @Override
    public int size() {
        return companies.size();
    }

    public boolean doesNotContain(ContactInfoItem item) {
        for (CompanyImpl company : list.get()){
            if (company.hasSharedContactInfoItem(item))
                return false;
        }
        return true;
    }
}

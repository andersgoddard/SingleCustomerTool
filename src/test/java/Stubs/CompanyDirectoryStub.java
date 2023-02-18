package Stubs;

import ContactInfo.ContactInfoItem;
import Directory.CompanyDirectory;
import Group.Company;

import java.util.List;

public class CompanyDirectoryStub implements CompanyDirectory {

    @Override
    public void add(Company associatable) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public List<Company> get() {
        return null;
    }

    @Override
    public boolean doesNotContain(ContactInfoItem item) {
        return false;
    }
}

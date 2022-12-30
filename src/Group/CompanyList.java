package Group;

import Contact.ContactList;

import java.util.ArrayList;

public class CompanyList {
    private static CompanyList list = null;
    private ArrayList<Company> companies;
    private CompanyList() {
        companies = new ArrayList<Company>();
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
}

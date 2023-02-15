package Associaters;

import Group.Company;

public interface ContactAssociater extends Associater<Company> {
    @Override
    void associate(Company company);
}

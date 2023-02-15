package Associaters;

import Group.CompanyImpl;

public interface ContactAssociater extends Associater<CompanyImpl> {
    @Override
    void associate(CompanyImpl company);
}

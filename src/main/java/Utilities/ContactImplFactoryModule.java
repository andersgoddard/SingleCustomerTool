package Utilities;

import Associaters.CompanyAssociater;
import Associaters.CompanyAssociaterImpl;
import Contact.BasicUniqueIdentifierGenerator;
import Contact.UniqueIdentifierGenerator;
import Directory.CompanyDirectory;
import Directory.CompanyDirectoryImpl;
import com.google.inject.AbstractModule;

public class ContactImplFactoryModule extends AbstractModule {
    CompanyDirectory companies;

    public ContactImplFactoryModule(CompanyDirectory companies){
        this.companies = companies;
    }

    @Override
    protected void configure() {
        bind(UniqueIdentifierGenerator.class).to(BasicUniqueIdentifierGenerator.class);
        bind(CompanyDirectory.class).to(CompanyDirectoryImpl.class);

        try {
            bind(CompanyAssociater.class)
                    .toConstructor(CompanyAssociaterImpl.class.getConstructor(CompanyDirectory.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
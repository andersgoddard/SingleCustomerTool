package Contact;

import Associaters.CompanyAssociater;
import Associaters.CompanyAssociaterImpl;
import com.google.inject.AbstractModule;

public class ContactFactoryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UniqueIdentifierGenerator.class).to(BasicUniqueIdentifierGenerator.class);
        bind(CompanyAssociater.class).to(CompanyAssociaterImpl.class);
    }
}

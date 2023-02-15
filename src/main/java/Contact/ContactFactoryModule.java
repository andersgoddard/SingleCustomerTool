package Contact;

import Associaters.Associater;
import Associaters.CompanyAssociaterImpl;
import com.google.inject.AbstractModule;

public class ContactFactoryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UniqueIdentifierGenerator.class).to(BasicUniqueIdentifierGenerator.class);
        bind(Associater.class).to(CompanyAssociaterImpl.class);
    }
}

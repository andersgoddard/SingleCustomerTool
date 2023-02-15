package Associaters;

import Contact.Contact;

public interface CompanyAssociater extends Associater<Contact> {
    @Override
    void associate(Contact contact);
}

package Stubs;

import ContactInfo.ContactInfoItem;

public class ContactInfoItemStub implements ContactInfoItem {
    @Override
    public String get() {
        return null;
    }

    @Override
    public String getOriginal() {
        return null;
    }

    @Override
    public void clean() {

    }

    @Override
    public boolean isEmail() {
        return false;
    }

    @Override
    public String getEmailDomain() {
        return null;
    }
}

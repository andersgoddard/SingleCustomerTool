package Stubs;

import ContactInfo.ContactInfo;
import ContactInfo.ContactInfoItem;

import java.util.ArrayList;
import java.util.List;

public class ContactInfoStub implements ContactInfo {
    @Override
    public void add(ContactInfoItem item) {

    }

    @Override
    public void addAll(ContactInfo info) {

    }

    @Override
    public boolean contains(ContactInfoItem item) {
        return false;
    }

    @Override
    public List<ContactInfoItem> getItems() {
        return new ArrayList<>();
    }
}

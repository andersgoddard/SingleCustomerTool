package Directory;

import ContactInfo.ContactInfoItem;

import java.util.List;

public interface Directory<T> {
    void add(T associatable);
    int size();
    void clear();
    List<T> get();
    boolean doesNotContain(ContactInfoItem item);
}

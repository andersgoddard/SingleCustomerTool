package Directory;

import Associaters.Associatable;
import ContactInfo.ContactInfoItem;

import java.util.List;

public interface Directory {
    void add(Associatable associatable);
    int size();
    void clear();
    List<Associatable> get();
    boolean doesNotContain(ContactInfoItem item);
}

package ContactInfo;

import java.util.List;

public interface ContactInfo {
    void add(ContactInfoItem item);

    void addAll(ContactInfo info);

    boolean contains(ContactInfoItem item);

    List<ContactInfoItem> getItems();
}

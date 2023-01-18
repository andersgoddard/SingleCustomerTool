package ContactInfo;

import java.util.ArrayList;

public interface Info {
    void add(ContactInfoItem item);

    void addAll(Info info);

    boolean contains(ContactInfoItem item);

    ArrayList<ContactInfoItem> getItems();

}
